var system_folder = 'http://livedemo00.template-help.com/wordpress_53995/wp-content/themes/CherryFramework/admin/data_management/',
	 CHILD_URL ='http://livedemo00.template-help.com/wordpress_53995/wp-content/themes/theme53995-child',
	 PARENT_URL = 'http://livedemo00.template-help.com/wordpress_53995/wp-content/themes/CherryFramework', 
	 CURRENT_THEME = 'theme53995_child'

		// Init navigation menu
			jQuery(function(){
			// main navigation init
				jQuery('ul.sf-menu').superfish({
					delay: 1000, // the delay in milliseconds that the mouse can remain outside a sub-menu without it closing
					animation: {
						opacity: "show",
						height: "show"
					}, // used to animate the sub-menu open
					speed: "normal", // animation speed
					autoArrows: false, // generation of arrow mark-up (for submenu)
					disableHI: true // to disable hoverIntent detection
				});

			//Zoom fix
			//IPad/IPhone
				var viewportmeta = document.querySelector && document.querySelector('meta[name="viewport"]'),
					ua = navigator.userAgent,
					gestureStart = function () {
						viewportmeta.content = "width=device-width, minimum-scale=0.25, maximum-scale=1.6, initial-scale=1.0";
					},
					scaleFix = function () {
						if (viewportmeta && /iPhone|iPad/.test(ua) && !/Opera Mini/.test(ua)) {
							viewportmeta.content = "width=device-width, minimum-scale=1.0, maximum-scale=1.0";
							document.addEventListener("gesturestart", gestureStart, false);
						}
					};
				scaleFix();
			})
			
			jQuery(document).ready(function(){
			if(!device.mobile() && !device.tablet()){
				jQuery('header .nav-wrap').tmStickUp({
					correctionSelector: jQuery('#wpadminbar')
				,	listenSelector: jQuery('.listenSelector')
				,	active: true				,	pseudo: true				});
			}
		})
		
		jQuery(function(){
		jQuery('.sf-menu').mobileMenu({defaultText: "Navigate to..."});
	});

window.NREUM||(NREUM={}),__nr_require=function(t,e,n){function r(n){if(!e[n]){var o=e[n]={exports:{}};t[n][0].call(o.exports,function(e){var o=t[n][1][e];return r(o||e)},o,o.exports)}return e[n].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<n.length;o++)r(n[o]);return r}({1:[function(t,e,n){function r(){}function o(t,e,n){return function(){return i(t,[(new Date).getTime()].concat(u(arguments)),e?null:this,n),e?void 0:this}}var i=t("handle"),a=t(2),u=t(3),c=t("ee").get("tracer"),f=NREUM;"undefined"==typeof window.newrelic&&(newrelic=f);var s=["setPageViewName","setCustomAttribute","finished","addToTrace","inlineHit"],p="api-",l=p+"ixn-";a(s,function(t,e){f[e]=o(p+e,!0,"api")}),f.addPageAction=o(p+"addPageAction",!0),e.exports=newrelic,f.interaction=function(){return(new r).get()};var d=r.prototype={createTracer:function(t,e){var n={},r=this,o="function"==typeof e;return i(l+"tracer",[Date.now(),t,n],r),function(){if(c.emit((o?"":"no-")+"fn-start",[Date.now(),r,o],n),o)try{return e.apply(this,arguments)}finally{c.emit("fn-end",[Date.now()],n)}}}};a("setName,setAttribute,save,ignore,onEnd,getContext,end,get".split(","),function(t,e){d[e]=o(l+e)}),newrelic.noticeError=function(t){"string"==typeof t&&(t=new Error(t)),i("err",[t,(new Date).getTime()])}},{}],2:[function(t,e,n){function r(t,e){var n=[],r="",i=0;for(r in t)o.call(t,r)&&(n[i]=e(r,t[r]),i+=1);return n}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],3:[function(t,e,n){function r(t,e,n){e||(e=0),"undefined"==typeof n&&(n=t?t.length:0);for(var r=-1,o=n-e||0,i=Array(o<0?0:o);++r<o;)i[r]=t[e+r];return i}e.exports=r},{}],ee:[function(t,e,n){function r(){}function o(t){function e(t){return t&&t instanceof r?t:t?u(t,a,i):i()}function n(n,r,o){t&&t(n,r,o);for(var i=e(o),a=l(n),u=a.length,c=0;c<u;c++)a[c].apply(i,r);var s=f[m[n]];return s&&s.push([w,n,r,i]),i}function p(t,e){g[t]=l(t).concat(e)}function l(t){return g[t]||[]}function d(t){return s[t]=s[t]||o(n)}function v(t,e){c(t,function(t,n){e=e||"feature",m[n]=e,e in f||(f[e]=[])})}var g={},m={},w={on:p,emit:n,get:d,listeners:l,context:e,buffer:v};return w}function i(){return new r}var a="nr@context",u=t("gos"),c=t(2),f={},s={},p=e.exports=o();p.backlog=f},{}],gos:[function(t,e,n){function r(t,e,n){if(o.call(t,e))return t[e];var r=n();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(t,e,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return t[e]=r,r}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],handle:[function(t,e,n){function r(t,e,n,r){o.buffer([t],r),o.emit(t,e,n)}var o=t("ee").get("handle");e.exports=r,r.ee=o},{}],id:[function(t,e,n){function r(t){var e=typeof t;return!t||"object"!==e&&"function"!==e?-1:t===window?0:a(t,i,function(){return o++})}var o=1,i="nr@id",a=t("gos");e.exports=r},{}],loader:[function(t,e,n){function r(){if(!h++){var t=y.info=NREUM.info,e=s.getElementsByTagName("script")[0];if(t&&t.licenseKey&&t.applicationID&&e){c(m,function(e,n){t[e]||(t[e]=n)});var n="https"===g.split(":")[0]||t.sslForHttp;y.proto=n?"https://":"http://",u("mark",["onload",a()],null,"api");var r=s.createElement("script");r.src=y.proto+t.agent,e.parentNode.insertBefore(r,e)}}}function o(){"complete"===s.readyState&&i()}function i(){u("mark",["domContent",a()],null,"api")}function a(){return(new Date).getTime()}var u=t("handle"),c=t(2),f=window,s=f.document,p="addEventListener",l="attachEvent",d=f.XMLHttpRequest,v=d&&d.prototype;NREUM.o={ST:setTimeout,CT:clearTimeout,XHR:d,REQ:f.Request,EV:f.Event,PR:f.Promise,MO:f.MutationObserver},t(1);var g=""+location,m={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-963.min.js"},w=d&&v&&v[p]&&!/CriOS/.test(navigator.userAgent),y=e.exports={offset:a(),origin:g,features:{},xhrWrappable:w};s[p]?(s[p]("DOMContentLoaded",i,!1),f[p]("load",r,!1)):(s[l]("onreadystatechange",o),f[l]("onload",r)),u("mark",["firstbyte",a()],null,"api");var h=0},{}]},{},["loader"]);

window._wpemojiSettings = {"baseUrl":"http:\/\/s.w.org\/images\/core\/emoji\/72x72\/","ext":".png","source":{"concatemoji":"http:\/\/livedemo00.template-help.com\/wordpress_53995\/wp-includes\/js\/wp-emoji-release.min.js?ver=4.2.10"}};
!function(a,b,c){function d(a){var c=b.createElement("canvas"),d=c.getContext&&c.getContext("2d");return d&&d.fillText?(d.textBaseline="top",d.font="600 32px Arial","flag"===a?(d.fillText(String.fromCharCode(55356,56812,55356,56807),0,0),c.toDataURL().length>3e3):(d.fillText(String.fromCharCode(55357,56835),0,0),0!==d.getImageData(16,16,1,1).data[0])):!1}function e(a){var c=b.createElement("script");c.src=a,c.type="text/javascript",b.getElementsByTagName("head")[0].appendChild(c)}var f,g;c.supports={simple:d("simple"),flag:d("flag")},c.DOMReady=!1,c.readyCallback=function(){c.DOMReady=!0},c.supports.simple&&c.supports.flag||(g=function(){c.readyCallback()},b.addEventListener?(b.addEventListener("DOMContentLoaded",g,!1),a.addEventListener("load",g,!1)):(a.attachEvent("onload",g),b.attachEvent("onreadystatechange",function(){"complete"===b.readyState&&c.readyCallback()})),f=c.source||{},f.concatemoji?e(f.concatemoji):f.wpemoji&&f.twemoji&&(e(f.twemoji),e(f.wpemoji)))}(window,document,window._wpemojiSettings);
window.olark||(function(c){var f=window,d=document,l=f.location.protocol=="https:"?"https:":"http:",z=c.name,r="load";var nt=function(){
	f[z]=function(){
	(a.s=a.s||[]).push(arguments)};var a=f[z]._={
	},q=c.methods.length;while(q--){(function(n){f[z][n]=function(){
	f[z]("call",n,arguments)}})(c.methods[q])}a.l=c.loader;a.i=nt;a.p={
	0:+new Date};a.P=function(u){
	a.p[u]=new Date-a.p[0]};function s(){
	a.P(r);f[z](r)}f.addEventListener?f.addEventListener(r,s,false):f.attachEvent("on"+r,s);var ld=function(){function p(hd){
	hd="head";return["<",hd,"></",hd,"><",i,' onl' + 'oad="var d=',g,";d.getElementsByTagName('head')[0].",j,"(d.",h,"('script')).",k,"='",l,"//",a.l,"'",'"',"></",i,">"].join("")}var i="body",m=d[i];if(!m){
	return setTimeout(ld,100)}a.P(1);var j="appendChild",h="createElement",k="src",n=d[h]("div"),v=n[j](d[h](z)),b=d[h]("iframe"),g="document",e="domain",o;n.style.display="none";m.insertBefore(n,m.firstChild).id=z;b.frameBorder="0";b.id=z+"-loader";if(/MSIE[ ]+6/.test(navigator.userAgent)){
	b.src="javascript:false"}b.allowTransparency="true";v[j](b);try{
	b.contentWindow[g].open()}catch(w){
	c[e]=d[e];o="javascript:var d="+g+".open();d.domain='"+d.domain+"';";b[k]=o+"void(0);"}try{
	var t=b.contentWindow[g];t.write(p());t.close()}catch(x){
	b[k]=o+'d.write("'+p().replace(/"/g,String.fromCharCode(92)+'"')+'");d.close();'}a.P(2)};ld()};nt()})({
	loader: "static.olark.com/jsclient/loader0.js",name:"olark",methods:["configure","extend","declare","identify"]});
	/* custom configuration goes here (www.olark.com/documentation) */
	olark.identify('7830-582-10-3714');
	
wplj(document).ready(function()
{
    wplj('#list_view').click(function()
    {
        wplj('#grid_view').removeClass('active');
        wplj('#list_view').addClass('active');
        
        wpl_set_property_css_class('row_box');
        
        wplj('.wpl_prp_cont').animate({opacity:0},function()
        {
            wplj(this).removeClass('grid_box').addClass('row_box');
            wplj(this).stop().animate({opacity:1});
        });
    });

    wplj('#grid_view').click(function()
    {
        wplj('#list_view').removeClass('active');
        wplj('#grid_view').addClass('active');
        
        wpl_set_property_css_class('grid_box');
        
        wplj('.wpl_prp_cont').animate({opacity:0},function()
        {
            wplj(this).removeClass('row_box').addClass('grid_box');
            wplj(this).stop().animate({opacity:1});
        });
    });
    
	main_win_size = wplj(window).width();
	if((main_win_size <= 480 ))
	{
		wplj('#wpl_property_listing_container .wpl_sort_options_container .wpl_sort_options_container_title').click(function()
		{
			wplj(this).next('ul').stop().slideToggle();
		});
	}

    if(!Modernizr.csstransitions)
    {
        wplj(".wpl_prp_top").hover(function ()
        {
            wplj(this).children('.wpl_prp_top_boxes.front').hide();
        },
        function()
        {
            wplj(this).children('.wpl_prp_top_boxes.front').show();
        });
    }

});

wplj(window).resize(function()
{
	win_size = wplj(window).width();
	if((win_size <= 480 ))
	{
		wplj('#wpl_property_listing_container .wpl_sort_options_container .wpl_sort_options_container_title').unbind('click').click(function()
		{
			wplj(this).next('ul').slideToggle();
		});
	}
	else if(win_size > 480)
	{
		wplj('#wpl_property_listing_container .wpl_sort_options_container .wpl_sort_options_container_title').unbind('click');
		wplj('#wpl_property_listing_container .wpl_sort_options_container ul').show();
	}	
});

function wpl_page_sortchange(order_string)
{
	url = 'http://livedemo00.template-help.com/wordpress_53995/';
	
	order_obj = order_string.split('&');
	
	order_v1 = order_obj[0].split('=');
	order_v2 = order_obj[1].split('=');
	
	url = wpl_update_qs(order_v1[0], order_v1[1], url);
	url = wpl_update_qs(order_v2[0], order_v2[1], url);
	
	window.location = url;
}

function wpl_pagesize_changed(page_size)
{
	url = 'http://livedemo00.template-help.com/wordpress_53995/';
	url = wpl_update_qs('limit', page_size, url);
    
	window.location = url;
}

var wpl_current_property_css_class;
function wpl_set_property_css_class(pcc)
{
    wpl_current_property_css_class = pcc;
    
    wplj.ajax(
    {
        url: 'http://livedemo00.template-help.com/wordpress_53995/',
        data: 'wpl_format=f:property_listing:ajax&wpl_function=set_pcc&pcc='+pcc,
        type: 'GET',
        dataType: 'json',
        cache: false,
        success: function(data)
        {
        }
    });
}

var wpl_map;
var markers_array = new Array();
var loaded_markers = new Array();
var markers;
var bounds;
var infowindow;

if(typeof google_place_radius == 'undefined') var google_place_radius = 1100;

var isDraggable = jQuery(document).width() > 768 ? true : false;

function wpl_initialize14()
{
	/** create empty LatLngBounds object **/
	bounds = new google.maps.LatLngBounds();
	var mapOptions = {
		scrollwheel: false,
		draggable: isDraggable,
		mapTypeId: google.maps.MapTypeId.WPL,
        	}
    
	/** init map **/
	wpl_map = new google.maps.Map(document.getElementById('wpl_map_canvas14'), mapOptions);
	infowindow = new google.maps.InfoWindow();
	
	/** load markers **/
	wpl_load_markers14(markers);
	
        var styles =
    [
        {
            "featureType": "water",
            "stylers": [
                {
                    "color": "#46bcec"
                },
                {
                    "visibility": "on"
                }
            ]
        },
        {
            "featureType": "landscape",
            "stylers": [
                {
                    "color": "#f2f2f2"
                }
            ]
        },
        {
            "featureType": "road",
            "stylers": [
                {
                    "saturation": -100
                },
                {
                    "lightness": 45
                }
            ]
        },
        {
            "featureType": "road.highway",
            "stylers": [
                {
                    "visibility": "simplified"
                }
            ]
        },
        {
            "featureType": "administrative",
            "elementType": "labels.text.fill",
            "stylers": [
                {
                    "color": "#444444"
                }
            ]
        },
        {
            "featureType": "poi",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        }
    ];

    var styledMap = new google.maps.StyledMapType(styles, {name: "WPL Map"});

    wpl_map.mapTypes.set('map_style', styledMap);
    wpl_map.setMapTypeId('map_style');
    
    /* Check Google Places */
	if((typeof google_place != 'undefined') && (google_place == 1))
	{
        var request = {
            location: marker.position,
            radius: google_place_radius
        };
  
		var service = new google.maps.places.PlacesService(wpl_map);
		service.search(request, wpl_gplace_callback14);
	}
    
    if(typeof wpl_dmgfc_init != 'undefined')
    {
        setTimeout('wpl_dmgfc_init()', 1000);
        jQuery('.wpl_map_canvas').append('<div class="wpl_dmgfc_container"></div>');
    }
}

function wpl_marker14(dataMarker)
{
	if(wplj.inArray(dataMarker.id, loaded_markers) != '-1') return true;
	
  	marker = new google.maps.Marker({
		position: new google.maps.LatLng(dataMarker.googlemap_lt, dataMarker.googlemap_ln),
		map: wpl_map,
		property_ids: dataMarker.pids,
		icon: 'http://livedemo00.template-help.com/wordpress_53995/wp-content/plugins/real-estate-listing-realtyna-wpl/assets/img/listing_types/gicon/'+dataMarker.gmap_icon,
		title: dataMarker.title,
	});
	
	/** extend the bounds to include each marker's position **/
  	bounds.extend(marker.position);
  
	loaded_markers.push(dataMarker.id);
  	markers_array.push(marker);
	
	google.maps.event.addListener(marker, "click", function(event)
	{
		if(this.html)
		{
			infowindow.close();
			infowindow.setContent(this.html);
			infowindow.open(wpl_map, this);
		}
		else
		{
			wplj("#wpl_map_canvas14").append('<div class="map_search_ajax_loader" style="position: absolute; top: 7px; left: 70px; z-index: 200;"><img src="http://livedemo00.template-help.com/wordpress_53995/wp-content/plugins/real-estate-listing-realtyna-wpl/assets/img/ajax-loader4.gif" /></div>');
			
			infowindow_html = get_infowindow_html14(this.property_ids);
			this.html = infowindow_html;
			infowindow.close();
			infowindow.setContent(infowindow_html);
			infowindow.open(wpl_map, this);
			
			wplj(".map_search_ajax_loader").remove();
		}
	});
}

function wpl_load_markers14(markers, delete_markers)
{
	if(delete_markers) delete_markers14();
	
	for(var i = 0; i < markers.length; i++)
	{
		wpl_marker14(markers[i]);
	}
    
	wpl_map.setZoom(1);
    
	if(!markers.length)
	{
		wpl_map.setCenter(new google.maps.LatLng(default_lt, default_ln));
		wpl_map.setZoom(parseInt(default_zoom));
	}
	else
	{
		/** now fit the map to the newly inclusive bounds **/
		wpl_map.fitBounds(bounds);
	}
}

function get_infowindow_html14(property_ids)
{
	var infowindow_html;
	
	wplj.ajax(
	{
		url: 'http://livedemo00.template-help.com/wordpress_53995/',
		data: 'wpl_format=c:functions:ajax&wpl_function=infowindow&property_ids='+property_ids+'&wpltarget=0',
		type: 'GET',
		async: false,
		cache: false,
		timeout: 30000,
		success: function(data)
		{
			infowindow_html = data;
		}
	});
	
	return infowindow_html;
}

function delete_markers14()
{
	if(markers_array)
	{
		for(i=0; i < markers_array.length; i++) markers_array[i].setMap(null);
		markers_array.length = 0;
	}
	
	if(loaded_markers) loaded_markers.length = 0;
}

function wpl_Plisting_slider(i, total_images, id)
{
    images_total = total_images;
    
    if ((i+1)>=images_total) j=0; else j=i+1;
    if (j==i) return;
    
    wplj("#wpl_gallery_image"+ id +"_"+i).fadeTo(200,0).css("display",'none');
    wplj("#wpl_gallery_image"+ id +"_"+j).fadeTo(400,1);
}

/** Google places functions **/
function wpl_gplace_callback14(results, status)
{
	if(status == google.maps.places.PlacesServiceStatus.OK)
	{
		for(var i=0; i<results.length; i++) wpl_gplace_marker14(results[i]);
	}
}

function wpl_gplace_marker14(place)
{
	var placeLoc = place.geometry.location;
	var image = new google.maps.MarkerImage(
  	place.icon, new google.maps.Size(51, 51),
  	new google.maps.Point(0, 0), new google.maps.Point(17, 34),
  	new google.maps.Size(25, 25));

	// create place types title
	var title_str = '';
    
	for(var i=0; i<place.types.length; i++)
	{
		title_str = title_str+place.types[i];
		if((i+1) != place.types.length) title_str = title_str+', ';
	}
    
	var marker = new google.maps.Marker(
    {
		map: wpl_map,
		icon: image,
		title: title_str,
		position: place.geometry.location
	});
    
    /** extend the bounds to include each marker's position **/
  	bounds.extend(place.geometry.location);
    
	google.maps.event.addListener(marker, 'click', function()
	{
		infowindow.setContent('<div class="wpl_gplace_infowindow_container" style="color: #000000;">'+place.name+'</div>');
		infowindow.open(wpl_map, this);
	});
}

