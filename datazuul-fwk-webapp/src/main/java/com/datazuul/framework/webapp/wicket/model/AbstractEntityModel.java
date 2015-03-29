package com.datazuul.framework.webapp.wicket.model;

import com.datazuul.framework.domain.DomainObject;
import java.io.Serializable;
import org.apache.wicket.model.IModel;

/**
 * A lot of Wicket applications use an ORM framework to work with the database.
 * Because ORMs provide a generic mechanism for loading entities we can create a
 * generic Wicket model that can simplify binding between ORM and Wicket. In
 * this article I will walk you through creating an EntityModel that will make
 * using Wicket and ORM fun. In order to load an entity from the database we
 * need two pieces of information: entity’s class and id. Since there is no
 * standard way to get an id from an object we will create an interface that
 * will standardize the process: DomainObject (has getId-method)
 *
 * This model can load any entity given an instance or a class and id, and will
 * detach itself at the end of request. The model is abstract because the way to
 * retrieve a handle to the ORM session varies from application to application.
 *
 * Additionally: <ul> <li>Ability to hold on to a transient entity instance
 * (disable detaching while the entity is transient)</li> <li>Allow the model to
 * intelligently begin detaching if a transient entity instance has become
 * persistent between getObject() and detach()</li> </ul> Since it is not
 * possible to override LoadableDetachableModel’s detach() we will have to
 * convert our model to implement IModel directly – giving us full control.
 *
 * From now on our EntityModel will hold on to the transient entity until it is
 * persisted. If we subclass the Form in the previous example and override its
 * onSubmit() to persist the entity the model will switch into persistent mode
 * after the form is submitted. So, it doesn’t matter if the EntityModel given
 * to that form contains a transient or a persistent instance of the entity, the
 * model and therefore also the form will simply do the right thing.
 *
 * @author Igor Vaynberg, Ralf Eichinger
 * @see http://wicketinaction.com/2008/09/building-a-smart-entitymodel/
 * @param <T> a domain object to be handled smartly
 */
public abstract class AbstractEntityModel<T extends DomainObject<?>> implements IModel<T> {

    private static final long serialVersionUID = 1L;
    private final Class clazz;
    private Serializable id;
    // cache for the entity instance that we keep for the request, only the
    // first #getObject() call will populate it
    private T entity;

    public AbstractEntityModel(T entity) {
        clazz = entity.getClass();
        id = entity.getId();
        // notice we initialize the cache if we have the instance
        this.entity = entity;
    }

    public AbstractEntityModel(Class<? extends T> clazz, Serializable id) {
        this.clazz = clazz;
        this.id = id;
    }

    @Override
    public T getObject() {
        if (entity == null) {
            // guard so we only call #load() if we have a valid id
            if (id != null) {
                entity = load(clazz, id);
                if (entity == null) {
                    /*
                     * There are cases when a user deletes an object from the database
                     * that another user is currently viewing in the UI. In this case
                     * when the UI tries to render and calls getObject() on our
                     * EntityModel it will return null. There is nothing worse in the
                     * log then a cryptic NullPointerExcepton. A much better approach is
                     * to check for this condition and throw a type of exception that can
                     * be intercepted and processed appropriately by the UI.
                     */
                    throw new EntityNotFoundException(clazz, id);
                }
            }
        }
        return entity;
    }

    @Override
    public void detach() {
        // guard from preventing detaching a detached model
        if (entity != null) {
            // guard to make sure we do not null out a transient entity instance,
            // we want to keep it until it is persisted
            if (entity.getId() != null) {
                // if a transient instance was persisted keep its id
                // so it can be reloaded
                id = entity.getId();
                // now that we have a valid id we no longer need to keep the cache
                entity = null;
            }
        }
    }

    /**
     * The method used to perform the actual loading of entity from the ORM
     *
     * @param clazz type of DomainObject
     * @param id unique id in database
     * @return loaded DomainObject
     */
    protected abstract T load(Class clazz, Serializable id);

    @Override
    public void setObject(T object) {
        throw new UnsupportedOperationException(getClass() + " does not support #setObject(T entity)");
    }
}
