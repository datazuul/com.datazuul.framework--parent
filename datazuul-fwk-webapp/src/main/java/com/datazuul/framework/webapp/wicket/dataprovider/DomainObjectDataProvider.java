package com.datazuul.framework.webapp.wicket.dataprovider;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import com.datazuul.framework.business.services.DomainObjectService;
import com.datazuul.framework.domain.DomainObject;
import com.datazuul.framework.webapp.wicket.model.LoadableDetachableDomainObjectModel;
import com.googlecode.genericdao.search.Search;

/**
 * note: it is important that the service passed to the data provider be a proxy from wicket-contrib-spring when used in
 * non-testing environment. this is because the dataprovider might get serialized for versioning or for replication
 * among the cluster and that would mean that the service will also be serialized. this is usually undesirable because
 * the service might have references to other objects and thus might cause a lot more to be serialized then is needed.
 * wicket-contrib-spring provides proxies to fix just this, the proxy only serializes information it needs to locate the
 * service when it is deserialized instead of serializing the service itself.
 *
 * @author Ralf Eichinger
 */
public class DomainObjectDataProvider<K extends Serializable, T extends DomainObject<K>, S> extends SortableDataProvider<T, S> {

    private static final long serialVersionUID = 1L;
    /**
     * reuse the Search entity to store filter information
     */
    private final Search search;
    /**
     * service that will be used to retrieve DomainObject(s)
     */
    private final DomainObjectService<K, T> service;

    public DomainObjectDataProvider(final DomainObjectService<K, T> service, final Search search) {
        super();
        this.service = service;
        this.search = search;

        // set the default sort
        //		List<Sort> sorts = search.getSorts();
        //		if (sorts != null) {
        //			Sort sort = sorts.get(0);
        //			setSort(sort.getProperty(), !sort.isDesc());
        //		}
    }

    @Override
    public Iterator<? extends T> iterator(final long first, final long count) {
        // search.getSorts();
        // SortParam sp = getSort();
        // if (queryParam == null) {
        // queryParam = new QueryParam(first, count);
        // } else {
        // queryParam.setFirst(first);
        // queryParam.setCount(count);
        // }
        // queryParam.setSort(sp.getProperty());
        // queryParam.setSortAsc(sp.isAscending());
        search.setFirstResult((int) first);
        final List<T> list = service.search(search);
        return list.iterator();
    }

    @Override
    public IModel<T> model(final T object) {
        return new LoadableDetachableDomainObjectModel<K>(object, service);
    }

    @Override
    public long size() {
        return service.count(search);
    }
}
