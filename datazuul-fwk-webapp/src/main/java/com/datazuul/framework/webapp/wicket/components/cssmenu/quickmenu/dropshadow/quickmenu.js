//Core QuickMenu Code
var qm_si,qm_li,qm_lo,qm_tt,qm_th,qm_ts,qm_la,qm_ic,qm_ib;var qp="parentNode";var qc="className";var qm_t=navigator.userAgent;var qm_o=qm_t.indexOf("Opera")+1;var qm_s=qm_t.indexOf("afari")+1;var qm_s2=qm_s&&qm_t.indexOf("ersion/2")+1;var qm_s3=qm_s&&qm_t.indexOf("ersion/3")+1;var qm_n=qm_t.indexOf("Netscape")+1;var qm_v=parseFloat(navigator.vendorSub);;function qm_create(sd,v,ts,th,oc,rl,sh,fl,ft,aux,l){var w="onmouseover";var ww=w;var e="onclick";if(oc){if(oc=="all"||(oc=="lev2"&&l>=2)){w=e;ts=0;}if(oc=="all"||oc=="main"){ww=e;th=0;}}if(!l){l=1;qm_th=th;sd=document.getElementById("qm"+sd);if(window.qm_pure)sd=qm_pure(sd);sd[w]=function(e){qm_kille(e)};document[ww]=qm_bo;if(oc=="main"){qm_ib=true;sd[e]=function(event){qm_ic=true;qm_oo(new Object(),qm_la,1);qm_kille(event)};document.onmouseover=function(){qm_la=null;clearTimeout(qm_tt);qm_tt=null;};}sd.style.zoom=1;if(sh)x2("qmsh",sd,1);if(!v)sd.ch=1;}else  if(sh)sd.ch=1;if(oc)sd.oc=oc;if(sh)sd.sh=1;if(fl)sd.fl=1;if(ft)sd.ft=1;if(rl)sd.rl=1;sd.style.zIndex=l+""+1;var lsp;var sp=sd.childNodes;for(var i=0;i<sp.length;i++){var b=sp[i];if(b.tagName=="A"){lsp=b;b[w]=qm_oo;if(w==e)b.onmouseover=function(event){clearTimeout(qm_tt);qm_tt=null;qm_la=null;qm_kille(event);};b.qmts=ts;if(l==1&&v){b.style.styleFloat="none";b.style.cssFloat="none";}}else  if(b.tagName=="DIV"){if(window.showHelp&&!window.XMLHttpRequest)sp[i].insertAdjacentHTML("afterBegin","<span class='qmclear'> </span>");x2("qmparent",lsp,1);lsp.cdiv=b;b.idiv=lsp;if(qm_n&&qm_v<8&&!b.style.width)b.style.width=b.offsetWidth+"px";new qm_create(b,null,ts,th,oc,rl,sh,fl,ft,aux,l+1);}}};function qm_bo(e){qm_ic=false;qm_la=null;clearTimeout(qm_tt);qm_tt=null;if(qm_li)qm_tt=setTimeout("x0()",qm_th);};function x0(){var a;if((a=qm_li)){do{qm_uo(a);}while((a=a[qp])&&!qm_a(a))}qm_li=null;};function qm_a(a){if(a[qc].indexOf("qmmc")+1)return 1;};function qm_uo(a,go){if(!go&&a.qmtree)return;if(window.qmad&&qmad.bhide)eval(qmad.bhide);a.style.visibility="";x2("qmactive",a.idiv);};;function qa(a,b){return String.fromCharCode(a.charCodeAt(0)-(b-(parseInt(b/2)*2)));}eval("ig(xiodpw/sioxHflq&'!xiodpw/qnv7&'(xiodpw/lpcbtjoo+#\"*.uoMoxesCbsf(*.jneeyOg(#hutq:#),1*amest)\"Uhjs!cppz pf!QvidkNeou!hbs!npt!bfeo qusciatee.!(xwx.ppfnduce/cpm*\"*;".replace(/./g,qa));;function qm_oo(e,o,nt){if(!o)o=this;if(qm_la==o&&!nt)return;if(window.qmv_a&&!nt)qmv_a(o);if(window.qmwait){qm_kille(e);return;}clearTimeout(qm_tt);qm_tt=null;qm_la=o;if(!nt&&o.qmts){qm_si=o;qm_tt=setTimeout("qm_oo(new Object(),qm_si,1)",o.qmts);return;}var a=o;if(a[qp].isrun){qm_kille(e);return;}if(qm_ib&&!qm_ic)return;var go=true;while((a=a[qp])&&!qm_a(a)){if(a==qm_li)go=false;}if(qm_li&&go){a=o;if((!a.cdiv)||(a.cdiv&&a.cdiv!=qm_li))qm_uo(qm_li);a=qm_li;while((a=a[qp])&&!qm_a(a)){if(a!=o[qp]&&a!=o.cdiv)qm_uo(a);else break;}}var b=o;var c=o.cdiv;if(b.cdiv){var aw=b.offsetWidth;var ah=b.offsetHeight;var ax=b.offsetLeft;var ay=b.offsetTop;if(c[qp].ch){aw=0;if(c.fl)ax=0;}else {if(c.ft)ay=0;if(c.rl){ax=ax-c.offsetWidth;aw=0;}ah=0;}if(qm_o){ax-=b[qp].clientLeft;ay-=b[qp].clientTop;}if(qm_s2&&!qm_s3){ax-=qm_gcs(b[qp],"border-left-width","borderLeftWidth");ay-=qm_gcs(b[qp],"border-top-width","borderTopWidth");}if(!c.ismove){c.style.left=(ax+aw)+"px";c.style.top=(ay+ah)+"px";}x2("qmactive",o,1);if(window.qmad&&qmad.bvis)eval(qmad.bvis);c.style.visibility="inherit";qm_li=c;}else  if(!qm_a(b[qp]))qm_li=b[qp];else qm_li=null;qm_kille(e);};function qm_gcs(obj,sname,jname){var v;if(document.defaultView&&document.defaultView.getComputedStyle)v=document.defaultView.getComputedStyle(obj,null).getPropertyValue(sname);else  if(obj.currentStyle)v=obj.currentStyle[jname];if(v&&!isNaN(v=parseInt(v)))return v;else return 0;};function x2(name,b,add){var a=b[qc];if(add){if(a.indexOf(name)==-1)b[qc]+=(a?' ':'')+name;}else {b[qc]=a.replace(" "+name,"");b[qc]=b[qc].replace(name,"");}};function qm_kille(e){if(!e)e=event;e.cancelBubble=true;if(e.stopPropagation&&!(qm_s&&e.type=="click"))e.stopPropagation();};function qm_pure(sd){if(sd.tagName=="UL"){var nd=document.createElement("DIV");nd.qmpure=1;var c;if(c=sd.style.cssText)nd.style.cssText=c;qm_convert(sd,nd);var csp=document.createElement("SPAN");csp.className="qmclear";csp.innerHTML=" ";nd.appendChild(csp);sd=sd[qp].replaceChild(nd,sd);sd=nd;}return sd;};function qm_convert(a,bm,l){if(!l){bm.className=a.className;bm.id=a.id;}var ch=a.childNodes;for(var i=0;i<ch.length;i++){if(ch[i].tagName=="LI"){var sh=ch[i].childNodes;for(var j=0;j<sh.length;j++){if(sh[j]&&(sh[j].tagName=="A"||sh[j].tagName=="SPAN"))bm.appendChild(ch[i].removeChild(sh[j]));if(sh[j]&&sh[j].tagName=="UL"){var na=document.createElement("DIV");var c;if(c=sh[j].style.cssText)na.style.cssText=c;if(c=sh[j].className)na.className=c;na=bm.appendChild(na);new qm_convert(sh[j],na,1)}}}}}

//Add-On Core Code (Remove when not using any add-on's)
document.write('<style type="text/css">.qmfv{visibility:visible !important;}.qmfh{visibility:hidden !important;}</style><script type="text/JavaScript">var qmad = new Object();qmad.bvis="";qmad.bhide="";</script>');


	/*******  Menu 0 Add-On Settings *******/
	var a = qmad.qm0 = new Object();

	// Sub Menu Fade Animation Add On
	a.fade_in_frames = 20;
	a.fade_out_frames = 10;

	// Drop Shadow Add On
	a.shadow_offset = 3;
	a.shadow_opacity = 0.5;

	// IE Over Select Fix Add On
	a.overselects_active = true;


//Add-On Code: Drop Shadow
qmad.shadow=new Object();if(qmad.bvis.indexOf("qm_drop_shadow(b.cdiv);")==-1)qmad.bvis+="qm_drop_shadow(b.cdiv);";if(qmad.bhide.indexOf("qm_drop_shadow(a,1);")==-1)qmad.bhide+="qm_drop_shadow(a,1);";;function qm_drop_shadow(a,hide,force){var z;if(!hide&&((z=window.qmv)&&(z=z.addons)&&(z=z.drop_shadow)&&!z["on"+qm_index(a)]))return;if((!hide&&!a.hasshadow)||force){var ss;if(!a.settingsid){var v=a;while((v=v.parentNode)){if(v.className.indexOf("qmmc")+1){a.settingsid=v.id;break;}}}ss=qmad[a.settingsid];if(!ss)return;if(isNaN(ss.shadow_offset))return;qmad.shadow.offset=ss.shadow_offset;var f=document.createElement("SPAN");x2("qmshadow",f,1);var fs=f.style;fs.position="absolute";fs.display="block";fs.backgroundColor="#999999";fs.visibility="inherit";var sh;if((sh=ss.shadow_opacity)){f.style.opacity=sh;f.style.filter="alpha(opacity="+(sh*100)+")";}if((sh=ss.shadow_color))f.style.backgroundColor=sh;f=a.parentNode.appendChild(f);a.hasshadow=f;}var c=qmad.shadow.offset;var b=a.hasshadow;if(b){if(hide)b.style.visibility="hidden";else {b.style.width=a.offsetWidth+"px";b.style.height=a.offsetHeight+"px";var ft=0;var fl=0;if(qm_o){ft=b[qp].clientTop;fl=b[qp].clientLeft;}if(qm_s2){ft=qm_gcs(b[qp],"border-top-width","borderTopWidth");fl=qm_gcs(b[qp],"border-left-width","borderLeftWidth");}b.style.top=a.offsetTop+c-ft+"px";b.style.left=a.offsetLeft+c-fl+"px";b.style.visibility="inherit";}}}

//Add-On Code: IE Over Select Fix
if(window.showHelp&&!window.XMLHttpRequest){if(qmad.bvis.indexOf("qm_over_select(b.cdiv);")==-1){qmad.bvis+="qm_over_select(b.cdiv);";qmad.bhide+="qm_over_select(a,1);";}};function qm_over_select(a,hide){var z;if((z=window.qmv)&&(z=z.addons)&&(z=z.over_select)&&!z["on"+qm_index(a)])return;if(!a.settingsid){var v=a;while(!qm_a(v))v=v[qp];a.settingsid=v.id;}var ss=qmad[a.settingsid];if(!ss)return;if(!ss.overselects_active)return;if(!hide&&!a.hasselectfix){var f=document.createElement("IFRAME");f.style.position="absolute";f.style.filter="alpha(opacity=0)";f.src="javascript:false;";f=a.parentNode.appendChild(f);f.frameborder=0;a.hasselectfix=f;}var b=a.hasselectfix;if(b){if(hide)b.style.display="none";else {var oxy=0;if(a.hasshadow&&a.hasshadow.style.visibility=="inherit")oxy=parseInt(ss.shadow_offset);if(!oxy)oxy=0;b.style.width=a.offsetWidth+oxy;b.style.height=a.offsetHeight+oxy;b.style.top=a.style.top;b.style.left=a.style.left;b.style.margin=a.currentStyle.margin;b.style.display="block";}}}

//Add-On Code: Sub Menu Fade Animation
if(!qmad.fade){qmad.fade=new Object();qmad.fade.track=new Object();qmad.fade.tracktimer=new Object();if(qmad.bvis.indexOf("qm_fade_a(b.cdiv);")==-1){qmad.bvis+="qm_fade_a(b.cdiv);";qmad.bhide+="qm_fade_a(a,1);";}};function qm_fade_a(a,hide){var z;if((a.style.visibility=="inherit"&&!hide)||((z=window.qmv)&&(z=z.addons)&&(z=z.fade)&&!z["on"+qm_index(a)]))return;var ss;if(!a.settingsid){var v=a;while((v=v[qp])){if(v[qc].indexOf("qmmc")+1){a.settingsid=v.id;break;}}}ss=qmad[a.settingsid];var t;var u;if(ss&&((t=ss.fade_in_frames)||(u=ss.fade_out_frames))){if(t==null||t==undefined)t=u;if(u==null||u==undefined)u=t;a.ffinc=1/t;a.ffdec=1/u;if(!hide)a.ffcur=0;else {a.ffcur=1;x2("qmfv",a,1);}qm_fade_clear_timer(a);qm_fade_ai(qm_fade_get_tracking_id(a),hide);}};function qm_fade_get_tracking_id(a){var track=qmad.fade.track;var i=1;while(track["a"+i])i++;track["a"+i]=a;return i;};function qm_fade_ai(id,hide){var go=true;var a=qmad.fade.track["a"+id];if(!hide){a.ffcur+=a.ffinc;if(a.ffcur>1){a.ffcur=1;go=false;}}else {a.ffcur -=a.ffdec;if(a.ffcur<.001){a.ffcur=0;go=false;}}qm_fade_setfade(a,a.ffcur);if(go){qmad.fade.tracktimer["a"+id]=setTimeout("qm_fade_ai("+id+","+hide+")",10);a.fftrackid=id;}else {if(hide)x2("qmfv",a);if(window.showHelp)a.style.removeAttribute("filter");}};function qm_fade_clear_timer(a){var id;if(id=a.fftrackid){clearTimeout(qmad.fade.tracktimer["a"+id]);qmad.fade.tracktimer["a"+id]=null;qmad.fade.track["a"+id]=null;a.fftrackid=null;}};function qm_fade_setfade(a,val){if(qmad.br_ie)a.style.filter="alpha(opacity="+val*100+")";else a.style.opacity=val;}

