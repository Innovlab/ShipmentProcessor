/*
 * angular-ui-bootstrap
 * http://angular-ui.github.io/bootstrap/

 * Version: 0.12.1 - 2015-02-20
 * License: MIT
 */
angular.module("ui.bootstrap",["ui.bootstrap.tpls","ui.bootstrap.tooltip","ui.bootstrap.position","ui.bootstrap.bindHtml","ui.bootstrap.typeahead","ui.bootstrap.modal","ui.bootstrap.transition","ui.bootstrap.pagination","ui.bootstrap.tabs"]),angular.module("ui.bootstrap.tpls",["template/tooltip/tooltip-html-unsafe-popup.html","template/tooltip/tooltip-popup.html","template/typeahead/typeahead-match.html","template/typeahead/typeahead-popup.html","template/modal/backdrop.html","template/modal/window.html","template/pagination/pager.html","template/pagination/pagination.html","template/tabs/tab.html","template/tabs/tabset.html"]),angular.module("ui.bootstrap.tooltip",["ui.bootstrap.position","ui.bootstrap.bindHtml"]).provider("$tooltip",function(){function t(t){var e=/[A-Z]/g,n="-";return t.replace(e,function(t,e){return(e?n:"")+t.toLowerCase()})}var e={placement:"top",animation:!0,popupDelay:0},n={mouseenter:"mouseleave",click:"click",focus:"blur"},a={};this.options=function(t){angular.extend(a,t)},this.setTriggers=function(t){angular.extend(n,t)},this.$get=["$window","$compile","$timeout","$document","$position","$interpolate",function(i,o,r,l,c,s){return function(i,p,u){function d(t){var e=t||f.trigger||u,a=n[e]||e;return{show:e,hide:a}}var f=angular.extend({},e,a),m=t(i),g=s.startSymbol(),h=s.endSymbol(),v="<div "+m+'-popup title="'+g+"title"+h+'" content="'+g+"content"+h+'" placement="'+g+"placement"+h+'" animation="animation" is-open="isOpen"></div>';return{restrict:"EA",compile:function(){var t=o(v);return function(e,n,a){function o(){U.isOpen?u():s()}function s(){(!A||e.$eval(a[p+"Enable"]))&&($(),U.popupDelay?P||(P=r(m,U.popupDelay,!1),P.then(function(t){t()})):m()())}function u(){e.$apply(function(){g()})}function m(){return P=null,T&&(r.cancel(T),T=null),U.content?(h(),k.css({top:0,left:0,display:"block"}),U.$digest(),S(),U.isOpen=!0,U.$digest(),S):angular.noop}function g(){U.isOpen=!1,r.cancel(P),P=null,U.animation?T||(T=r(v,500)):v()}function h(){k&&v(),x=U.$new(),k=t(x,function(t){E?l.find("body").append(t):n.after(t)})}function v(){T=null,k&&(k.remove(),k=null),x&&(x.$destroy(),x=null)}function $(){b(),y()}function b(){var t=a[p+"Placement"];U.placement=angular.isDefined(t)?t:f.placement}function y(){var t=a[p+"PopupDelay"],e=parseInt(t,10);U.popupDelay=isNaN(e)?f.popupDelay:e}function w(){var t=a[p+"Trigger"];M(),C=d(t),C.show===C.hide?n.bind(C.show,o):(n.bind(C.show,s),n.bind(C.hide,u))}var k,x,T,P,E=angular.isDefined(f.appendToBody)?f.appendToBody:!1,C=d(void 0),A=angular.isDefined(a[p+"Enable"]),U=e.$new(!0),S=function(){var t=c.positionElements(n,k,U.placement,E);t.top+="px",t.left+="px",k.css(t)};U.isOpen=!1,a.$observe(i,function(t){U.content=t,!t&&U.isOpen&&g()}),a.$observe(p+"Title",function(t){U.title=t});var M=function(){n.unbind(C.show,s),n.unbind(C.hide,u)};w();var D=e.$eval(a[p+"Animation"]);U.animation=angular.isDefined(D)?!!D:f.animation;var I=e.$eval(a[p+"AppendToBody"]);E=angular.isDefined(I)?I:E,E&&e.$on("$locationChangeSuccess",function(){U.isOpen&&g()}),e.$on("$destroy",function(){r.cancel(T),r.cancel(P),M(),v(),U=null})}}}}}]}).directive("tooltipPopup",function(){return{restrict:"EA",replace:!0,scope:{content:"@",placement:"@",animation:"&",isOpen:"&"},templateUrl:"template/tooltip/tooltip-popup.html"}}).directive("tooltip",["$tooltip",function(t){return t("tooltip","tooltip","mouseenter")}]).directive("tooltipHtmlUnsafePopup",function(){return{restrict:"EA",replace:!0,scope:{content:"@",placement:"@",animation:"&",isOpen:"&"},templateUrl:"template/tooltip/tooltip-html-unsafe-popup.html"}}).directive("tooltipHtmlUnsafe",["$tooltip",function(t){return t("tooltipHtmlUnsafe","tooltip","mouseenter")}]),angular.module("ui.bootstrap.position",[]).factory("$position",["$document","$window",function(t,e){function n(t,n){return t.currentStyle?t.currentStyle[n]:e.getComputedStyle?e.getComputedStyle(t)[n]:t.style[n]}function a(t){return"static"===(n(t,"position")||"static")}var i=function(e){for(var n=t[0],i=e.offsetParent||n;i&&i!==n&&a(i);)i=i.offsetParent;return i||n};return{position:function(e){var n=this.offset(e),a={top:0,left:0},o=i(e[0]);o!=t[0]&&(a=this.offset(angular.element(o)),a.top+=o.clientTop-o.scrollTop,a.left+=o.clientLeft-o.scrollLeft);var r=e[0].getBoundingClientRect();return{width:r.width||e.prop("offsetWidth"),height:r.height||e.prop("offsetHeight"),top:n.top-a.top,left:n.left-a.left}},offset:function(n){var a=n[0].getBoundingClientRect();return{width:a.width||n.prop("offsetWidth"),height:a.height||n.prop("offsetHeight"),top:a.top+(e.pageYOffset||t[0].documentElement.scrollTop),left:a.left+(e.pageXOffset||t[0].documentElement.scrollLeft)}},positionElements:function(t,e,n,a){var i,o,r,l,c=n.split("-"),s=c[0],p=c[1]||"center";i=a?this.offset(t):this.position(t),o=e.prop("offsetWidth"),r=e.prop("offsetHeight");var u={center:function(){return i.left+i.width/2-o/2},left:function(){return i.left},right:function(){return i.left+i.width}},d={center:function(){return i.top+i.height/2-r/2},top:function(){return i.top},bottom:function(){return i.top+i.height}};switch(s){case"right":l={top:d[p](),left:u[s]()};break;case"left":l={top:d[p](),left:i.left-o};break;case"bottom":l={top:d[s](),left:u[p]()};break;default:l={top:i.top-r,left:u[p]()}}return l}}}]),angular.module("ui.bootstrap.bindHtml",[]).directive("bindHtmlUnsafe",function(){return function(t,e,n){e.addClass("ng-binding").data("$binding",n.bindHtmlUnsafe),t.$watch(n.bindHtmlUnsafe,function(t){e.html(t||"")})}}),angular.module("ui.bootstrap.typeahead",["ui.bootstrap.position","ui.bootstrap.bindHtml"]).factory("typeaheadParser",["$parse",function(t){var e=/^\s*([\s\S]+?)(?:\s+as\s+([\s\S]+?))?\s+for\s+(?:([\$\w][\$\w\d]*))\s+in\s+([\s\S]+?)$/;return{parse:function(n){var a=n.match(e);if(!a)throw new Error('Expected typeahead specification in form of "_modelValue_ (as _label_)? for _item_ in _collection_" but got "'+n+'".');return{itemName:a[3],source:t(a[4]),viewMapper:t(a[2]||a[1]),modelMapper:t(a[1])}}}}]).directive("typeahead",["$compile","$parse","$q","$timeout","$document","$position","typeaheadParser",function(t,e,n,a,i,o,r){var l=[9,13,27,38,40];return{require:"ngModel",link:function(c,s,p,u){var d,f=c.$eval(p.typeaheadMinLength)||1,m=c.$eval(p.typeaheadWaitMs)||0,g=c.$eval(p.typeaheadEditable)!==!1,h=e(p.typeaheadLoading).assign||angular.noop,v=e(p.typeaheadOnSelect),$=p.typeaheadInputFormatter?e(p.typeaheadInputFormatter):void 0,b=p.typeaheadAppendToBody?c.$eval(p.typeaheadAppendToBody):!1,y=c.$eval(p.typeaheadFocusFirst)!==!1,w=e(p.ngModel).assign,k=r.parse(p.typeahead),x=c.$new();c.$on("$destroy",function(){x.$destroy()});var T="typeahead-"+x.$id+"-"+Math.floor(1e4*Math.random());s.attr({"aria-autocomplete":"list","aria-expanded":!1,"aria-owns":T});var P=angular.element("<div typeahead-popup></div>");P.attr({id:T,matches:"matches",active:"activeIdx",select:"select(activeIdx)",query:"query",position:"position"}),angular.isDefined(p.typeaheadTemplateUrl)&&P.attr("template-url",p.typeaheadTemplateUrl);var E=function(){x.matches=[],x.activeIdx=-1,s.attr("aria-expanded",!1)},C=function(t){return T+"-option-"+t};x.$watch("activeIdx",function(t){0>t?s.removeAttr("aria-activedescendant"):s.attr("aria-activedescendant",C(t))});var A=function(t){var e={$viewValue:t};h(c,!0),n.when(k.source(c,e)).then(function(n){var a=t===u.$viewValue;if(a&&d)if(n.length>0){x.activeIdx=y?0:-1,x.matches.length=0;for(var i=0;i<n.length;i++)e[k.itemName]=n[i],x.matches.push({id:C(i),label:k.viewMapper(x,e),model:n[i]});x.query=t,x.position=b?o.offset(s):o.position(s),x.position.top=x.position.top+s.prop("offsetHeight"),s.attr("aria-expanded",!0)}else E();a&&h(c,!1)},function(){E(),h(c,!1)})};E(),x.query=void 0;var U,S=function(t){U=a(function(){A(t)},m)},M=function(){U&&a.cancel(U)};u.$parsers.unshift(function(t){return d=!0,t&&t.length>=f?m>0?(M(),S(t)):A(t):(h(c,!1),M(),E()),g?t:t?void u.$setValidity("editable",!1):(u.$setValidity("editable",!0),t)}),u.$formatters.push(function(t){var e,n,a={};return $?(a.$model=t,$(c,a)):(a[k.itemName]=t,e=k.viewMapper(c,a),a[k.itemName]=void 0,n=k.viewMapper(c,a),e!==n?e:t)}),x.select=function(t){var e,n,i={};i[k.itemName]=n=x.matches[t].model,e=k.modelMapper(c,i),w(c,e),u.$setValidity("editable",!0),v(c,{$item:n,$model:e,$label:k.viewMapper(c,i)}),E(),a(function(){s[0].focus()},0,!1)},s.bind("keydown",function(t){0!==x.matches.length&&-1!==l.indexOf(t.which)&&(-1!=x.activeIdx||13!==t.which&&9!==t.which)&&(t.preventDefault(),40===t.which?(x.activeIdx=(x.activeIdx+1)%x.matches.length,x.$digest()):38===t.which?(x.activeIdx=(x.activeIdx>0?x.activeIdx:x.matches.length)-1,x.$digest()):13===t.which||9===t.which?x.$apply(function(){x.select(x.activeIdx)}):27===t.which&&(t.stopPropagation(),E(),x.$digest()))}),s.bind("blur",function(){d=!1});var D=function(t){s[0]!==t.target&&(E(),x.$digest())};i.bind("click",D),c.$on("$destroy",function(){i.unbind("click",D),b&&I.remove()});var I=t(P)(x);b?i.find("body").append(I):s.after(I)}}}]).directive("typeaheadPopup",function(){return{restrict:"EA",scope:{matches:"=",query:"=",active:"=",position:"=",select:"&"},replace:!0,templateUrl:"template/typeahead/typeahead-popup.html",link:function(t,e,n){t.templateUrl=n.templateUrl,t.isOpen=function(){return t.matches.length>0},t.isActive=function(e){return t.active==e},t.selectActive=function(e){t.active=e},t.selectMatch=function(e){t.select({activeIdx:e})}}}}).directive("typeaheadMatch",["$http","$templateCache","$compile","$parse",function(t,e,n,a){return{restrict:"EA",scope:{index:"=",match:"=",query:"="},link:function(i,o,r){var l=a(r.templateUrl)(i.$parent)||"template/typeahead/typeahead-match.html";t.get(l,{cache:e}).success(function(t){o.replaceWith(n(t.trim())(i))})}}}]).filter("typeaheadHighlight",function(){function t(t){return t.replace(/([.?*+^$[\]\\(){}|-])/g,"\\$1")}return function(e,n){return n?(""+e).replace(new RegExp(t(n),"gi"),"<strong>$&</strong>"):e}}),angular.module("ui.bootstrap.modal",["ui.bootstrap.transition"]).factory("$$stackedMap",function(){return{createNew:function(){var t=[];return{add:function(e,n){t.push({key:e,value:n})},get:function(e){for(var n=0;n<t.length;n++)if(e==t[n].key)return t[n]},keys:function(){for(var e=[],n=0;n<t.length;n++)e.push(t[n].key);return e},top:function(){return t[t.length-1]},remove:function(e){for(var n=-1,a=0;a<t.length;a++)if(e==t[a].key){n=a;break}return t.splice(n,1)[0]},removeTop:function(){return t.splice(t.length-1,1)[0]},length:function(){return t.length}}}}}).directive("modalBackdrop",["$timeout",function(t){return{restrict:"EA",replace:!0,templateUrl:"template/modal/backdrop.html",link:function(e,n,a){e.backdropClass=a.backdropClass||"",e.animate=!1,t(function(){e.animate=!0})}}}]).directive("modalWindow",["$modalStack","$timeout",function(t,e){return{restrict:"EA",scope:{index:"@",animate:"="},replace:!0,transclude:!0,templateUrl:function(t,e){return e.templateUrl||"template/modal/window.html"},link:function(n,a,i){a.addClass(i.windowClass||""),n.size=i.size,e(function(){n.animate=!0,a[0].querySelectorAll("[autofocus]").length||a[0].focus()}),n.close=function(e){var n=t.getTop();n&&n.value.backdrop&&"static"!=n.value.backdrop&&e.target===e.currentTarget&&(e.preventDefault(),e.stopPropagation(),t.dismiss(n.key,"backdrop click"))}}}}]).directive("modalTransclude",function(){return{link:function(t,e,n,a,i){i(t.$parent,function(t){e.empty(),e.append(t)})}}}).factory("$modalStack",["$transition","$timeout","$document","$compile","$rootScope","$$stackedMap",function(t,e,n,a,i,o){function r(){for(var t=-1,e=f.keys(),n=0;n<e.length;n++)f.get(e[n]).value.backdrop&&(t=n);return t}function l(t){var e=n.find("body").eq(0),a=f.get(t).value;f.remove(t),s(a.modalDomEl,a.modalScope,300,function(){a.modalScope.$destroy(),e.toggleClass(d,f.length()>0),c()})}function c(){if(p&&-1==r()){var t=u;s(p,u,150,function(){t.$destroy(),t=null}),p=void 0,u=void 0}}function s(n,a,i,o){function r(){r.done||(r.done=!0,n.remove(),o&&o())}a.animate=!1;var l=t.transitionEndEventName;if(l){var c=e(r,i);n.bind(l,function(){e.cancel(c),r(),a.$apply()})}else e(r)}var p,u,d="modal-open",f=o.createNew(),m={};return i.$watch(r,function(t){u&&(u.index=t)}),n.bind("keydown",function(t){var e;27===t.which&&(e=f.top(),e&&e.value.keyboard&&(t.preventDefault(),i.$apply(function(){m.dismiss(e.key,"escape key press")})))}),m.open=function(t,e){f.add(t,{deferred:e.deferred,modalScope:e.scope,backdrop:e.backdrop,keyboard:e.keyboard});var o=n.find("body").eq(0),l=r();if(l>=0&&!p){u=i.$new(!0),u.index=l;var c=angular.element("<div modal-backdrop></div>");c.attr("backdrop-class",e.backdropClass),p=a(c)(u),o.append(p)}var s=angular.element("<div modal-window></div>");s.attr({"template-url":e.windowTemplateUrl,"window-class":e.windowClass,size:e.size,index:f.length()-1,animate:"animate"}).html(e.content);var m=a(s)(e.scope);f.top().value.modalDomEl=m,o.append(m),o.addClass(d)},m.close=function(t,e){var n=f.get(t);n&&(n.value.deferred.resolve(e),l(t))},m.dismiss=function(t,e){var n=f.get(t);n&&(n.value.deferred.reject(e),l(t))},m.dismissAll=function(t){for(var e=this.getTop();e;)this.dismiss(e.key,t),e=this.getTop()},m.getTop=function(){return f.top()},m}]).provider("$modal",function(){var t={options:{backdrop:!0,keyboard:!0},$get:["$injector","$rootScope","$q","$http","$templateCache","$controller","$modalStack",function(e,n,a,i,o,r,l){function c(t){return t.template?a.when(t.template):i.get(angular.isFunction(t.templateUrl)?t.templateUrl():t.templateUrl,{cache:o}).then(function(t){return t.data})}function s(t){var n=[];return angular.forEach(t,function(t){(angular.isFunction(t)||angular.isArray(t))&&n.push(a.when(e.invoke(t)))}),n}var p={};return p.open=function(e){var i=a.defer(),o=a.defer(),p={result:i.promise,opened:o.promise,close:function(t){l.close(p,t)},dismiss:function(t){l.dismiss(p,t)}};if(e=angular.extend({},t.options,e),e.resolve=e.resolve||{},!e.template&&!e.templateUrl)throw new Error("One of template or templateUrl options is required.");var u=a.all([c(e)].concat(s(e.resolve)));return u.then(function(t){var a=(e.scope||n).$new();a.$close=p.close,a.$dismiss=p.dismiss;var o,c={},s=1;e.controller&&(c.$scope=a,c.$modalInstance=p,angular.forEach(e.resolve,function(e,n){c[n]=t[s++]}),o=r(e.controller,c),e.controllerAs&&(a[e.controllerAs]=o)),l.open(p,{scope:a,deferred:i,content:t[0],backdrop:e.backdrop,keyboard:e.keyboard,backdropClass:e.backdropClass,windowClass:e.windowClass,windowTemplateUrl:e.windowTemplateUrl,size:e.size})},function(t){i.reject(t)}),u.then(function(){o.resolve(!0)},function(){o.reject(!1)}),p},p}]};return t}),angular.module("ui.bootstrap.transition",[]).factory("$transition",["$q","$timeout","$rootScope",function(t,e,n){function a(t){for(var e in t)if(void 0!==o.style[e])return t[e]}var i=function(a,o,r){r=r||{};var l=t.defer(),c=i[r.animation?"animationEndEventName":"transitionEndEventName"],s=function(){n.$apply(function(){a.unbind(c,s),l.resolve(a)})};return c&&a.bind(c,s),e(function(){angular.isString(o)?a.addClass(o):angular.isFunction(o)?o(a):angular.isObject(o)&&a.css(o),c||l.resolve(a)}),l.promise.cancel=function(){c&&a.unbind(c,s),l.reject("Transition cancelled")},l.promise},o=document.createElement("trans"),r={WebkitTransition:"webkitTransitionEnd",MozTransition:"transitionend",OTransition:"oTransitionEnd",transition:"transitionend"},l={WebkitTransition:"webkitAnimationEnd",MozTransition:"animationend",OTransition:"oAnimationEnd",transition:"animationend"};return i.transitionEndEventName=a(r),i.animationEndEventName=a(l),i}]),angular.module("ui.bootstrap.pagination",[]).controller("PaginationController",["$scope","$attrs","$parse",function(t,e,n){var a=this,i={$setViewValue:angular.noop},o=e.numPages?n(e.numPages).assign:angular.noop;this.init=function(o,r){i=o,this.config=r,i.$render=function(){a.render()},e.itemsPerPage?t.$parent.$watch(n(e.itemsPerPage),function(e){a.itemsPerPage=parseInt(e,10),t.totalPages=a.calculateTotalPages()}):this.itemsPerPage=r.itemsPerPage},this.calculateTotalPages=function(){var e=this.itemsPerPage<1?1:Math.ceil(t.totalItems/this.itemsPerPage);return Math.max(e||0,1)},this.render=function(){t.page=parseInt(i.$viewValue,10)||1},t.selectPage=function(e){t.page!==e&&e>0&&e<=t.totalPages&&(i.$setViewValue(e),i.$render())},t.getText=function(e){return t[e+"Text"]||a.config[e+"Text"]},t.noPrevious=function(){return 1===t.page},t.noNext=function(){return t.page===t.totalPages},t.$watch("totalItems",function(){t.totalPages=a.calculateTotalPages()}),t.$watch("totalPages",function(e){o(t.$parent,e),t.page>e?t.selectPage(e):i.$render()})}]).constant("paginationConfig",{itemsPerPage:10,boundaryLinks:!1,directionLinks:!0,firstText:"First",previousText:"Previous",nextText:"Next",lastText:"Last",rotate:!0}).directive("pagination",["$parse","paginationConfig",function(t,e){return{restrict:"EA",scope:{totalItems:"=",firstText:"@",previousText:"@",nextText:"@",lastText:"@"},require:["pagination","?ngModel"],controller:"PaginationController",templateUrl:"template/pagination/pagination.html",replace:!0,link:function(n,a,i,o){function r(t,e,n){return{number:t,text:e,active:n}}function l(t,e){var n=[],a=1,i=e,o=angular.isDefined(p)&&e>p;o&&(u?(a=Math.max(t-Math.floor(p/2),1),i=a+p-1,i>e&&(i=e,a=i-p+1)):(a=(Math.ceil(t/p)-1)*p+1,i=Math.min(a+p-1,e)));for(var l=a;i>=l;l++){var c=r(l,l,l===t);n.push(c)}if(o&&!u){if(a>1){var s=r(a-1,"...",!1);n.unshift(s)}if(e>i){var d=r(i+1,"...",!1);n.push(d)}}return n}var c=o[0],s=o[1];if(s){var p=angular.isDefined(i.maxSize)?n.$parent.$eval(i.maxSize):e.maxSize,u=angular.isDefined(i.rotate)?n.$parent.$eval(i.rotate):e.rotate;n.boundaryLinks=angular.isDefined(i.boundaryLinks)?n.$parent.$eval(i.boundaryLinks):e.boundaryLinks,n.directionLinks=angular.isDefined(i.directionLinks)?n.$parent.$eval(i.directionLinks):e.directionLinks,c.init(s,e),i.maxSize&&n.$parent.$watch(t(i.maxSize),function(t){p=parseInt(t,10),c.render()});var d=c.render;c.render=function(){d(),n.page>0&&n.page<=n.totalPages&&(n.pages=l(n.page,n.totalPages))}}}}}]).constant("pagerConfig",{itemsPerPage:10,previousText:"� Previous",nextText:"Next �",align:!0}).directive("pager",["pagerConfig",function(t){return{restrict:"EA",scope:{totalItems:"=",previousText:"@",nextText:"@"},require:["pager","?ngModel"],controller:"PaginationController",templateUrl:"template/pagination/pager.html",replace:!0,link:function(e,n,a,i){var o=i[0],r=i[1];r&&(e.align=angular.isDefined(a.align)?e.$parent.$eval(a.align):t.align,o.init(r,t))}}}]),angular.module("ui.bootstrap.tabs",[]).controller("TabsetController",["$scope",function(t){var e=this,n=e.tabs=t.tabs=[];e.select=function(t){angular.forEach(n,function(e){e.active&&e!==t&&(e.active=!1,e.onDeselect())}),t.active=!0,t.onSelect()},e.addTab=function(t){n.push(t),1===n.length?t.active=!0:t.active&&e.select(t)},e.removeTab=function(t){var i=n.indexOf(t);if(t.active&&n.length>1&&!a){var o=i==n.length-1?i-1:i+1;e.select(n[o])}n.splice(i,1)};var a;t.$on("$destroy",function(){a=!0})}]).directive("tabset",function(){return{restrict:"EA",transclude:!0,replace:!0,scope:{type:"@"},controller:"TabsetController",templateUrl:"template/tabs/tabset.html",link:function(t,e,n){t.vertical=angular.isDefined(n.vertical)?t.$parent.$eval(n.vertical):!1,t.justified=angular.isDefined(n.justified)?t.$parent.$eval(n.justified):!1}}}).directive("tab",["$parse",function(t){return{require:"^tabset",restrict:"EA",replace:!0,templateUrl:"template/tabs/tab.html",transclude:!0,scope:{active:"=?",heading:"@",onSelect:"&select",onDeselect:"&deselect"},controller:function(){},compile:function(e,n,a){return function(e,n,i,o){e.$watch("active",function(t){t&&o.select(e)}),e.disabled=!1,i.disabled&&e.$parent.$watch(t(i.disabled),function(t){e.disabled=!!t}),e.select=function(){e.disabled||(e.active=!0)},o.addTab(e),e.$on("$destroy",function(){o.removeTab(e)}),e.$transcludeFn=a}}}}]).directive("tabHeadingTransclude",[function(){return{restrict:"A",require:"^tab",link:function(t,e){t.$watch("headingElement",function(t){t&&(e.html(""),e.append(t))})}}}]).directive("tabContentTransclude",function(){function t(t){return t.tagName&&(t.hasAttribute("tab-heading")||t.hasAttribute("data-tab-heading")||"tab-heading"===t.tagName.toLowerCase()||"data-tab-heading"===t.tagName.toLowerCase())}return{restrict:"A",require:"^tabset",link:function(e,n,a){var i=e.$eval(a.tabContentTransclude);i.$transcludeFn(i.$parent,function(e){angular.forEach(e,function(e){t(e)?i.headingElement=e:n.append(e)})})}}}),angular.module("template/tooltip/tooltip-html-unsafe-popup.html",[]).run(["$templateCache",function(t){t.put("template/tooltip/tooltip-html-unsafe-popup.html",'<div class="tooltip {{placement}}" ng-class="{ in: isOpen(), fade: animation() }">\n  <div class="tooltip-arrow"></div>\n  <div class="tooltip-inner" bind-html-unsafe="content"></div>\n</div>\n')}]),angular.module("template/tooltip/tooltip-popup.html",[]).run(["$templateCache",function(t){t.put("template/tooltip/tooltip-popup.html",'<div class="tooltip {{placement}}" ng-class="{ in: isOpen(), fade: animation() }">\n  <div class="tooltip-arrow"></div>\n  <div class="tooltip-inner" ng-bind="content"></div>\n</div>\n')}]),angular.module("template/typeahead/typeahead-match.html",[]).run(["$templateCache",function(t){t.put("template/typeahead/typeahead-match.html",'<a tabindex="-1" bind-html-unsafe="match.label | typeaheadHighlight:query"></a>')}]),angular.module("template/typeahead/typeahead-popup.html",[]).run(["$templateCache",function(t){t.put("template/typeahead/typeahead-popup.html",'<ul class="dropdown-menu" ng-show="isOpen()" ng-style="{top: position.top+\'px\', left: position.left+\'px\'}" style="display: block;" role="listbox" aria-hidden="{{!isOpen()}}">\n    <li ng-repeat="match in matches track by $index" ng-class="{active: isActive($index) }" ng-mouseenter="selectActive($index)" ng-click="selectMatch($index)" role="option" id="{{match.id}}">\n        <div typeahead-match index="$index" match="match" query="query" template-url="templateUrl"></div>\n    </li>\n</ul>\n')}]),angular.module("template/modal/backdrop.html",[]).run(["$templateCache",function(t){t.put("template/modal/backdrop.html",'<div class="modal-backdrop fade {{ backdropClass }}"\n     ng-class="{in: animate}"\n     ng-style="{\'z-index\': 1040 + (index && 1 || 0) + index*10}"\n></div>\n')}]),angular.module("template/modal/window.html",[]).run(["$templateCache",function(t){t.put("template/modal/window.html",'<div tabindex="-1" role="dialog" class="modal fade" ng-class="{in: animate}" ng-style="{\'z-index\': 1050 + index*10, display: \'block\'}" ng-click="close($event)">\n    <div class="modal-dialog" ng-class="{\'modal-sm\': size == \'sm\', \'modal-lg\': size == \'lg\'}"><div class="modal-content" modal-transclude></div></div>\n</div>')}]),angular.module("template/pagination/pager.html",[]).run(["$templateCache",function(t){t.put("template/pagination/pager.html",'<ul class="pager">\n  <li ng-class="{disabled: noPrevious(), previous: align}"><a href ng-click="selectPage(page - 1)">{{getText(\'previous\')}}</a></li>\n  <li ng-class="{disabled: noNext(), next: align}"><a href ng-click="selectPage(page + 1)">{{getText(\'next\')}}</a></li>\n</ul>')}]),angular.module("template/pagination/pagination.html",[]).run(["$templateCache",function(t){t.put("template/pagination/pagination.html",'<ul class="pagination">\n  <li ng-if="boundaryLinks" ng-class="{disabled: noPrevious()}"><a href ng-click="selectPage(1)">{{getText(\'first\')}}</a></li>\n  <li ng-if="directionLinks" ng-class="{disabled: noPrevious()}"><a href ng-click="selectPage(page - 1)">{{getText(\'previous\')}}</a></li>\n  <li ng-repeat="page in pages track by $index" ng-class="{active: page.active}"><a href ng-click="selectPage(page.number)">{{page.text}}</a></li>\n  <li ng-if="directionLinks" ng-class="{disabled: noNext()}"><a href ng-click="selectPage(page + 1)">{{getText(\'next\')}}</a></li>\n  <li ng-if="boundaryLinks" ng-class="{disabled: noNext()}"><a href ng-click="selectPage(totalPages)">{{getText(\'last\')}}</a></li>\n</ul>')}]),angular.module("template/tabs/tab.html",[]).run(["$templateCache",function(t){t.put("template/tabs/tab.html",'<li ng-class="{active: active, disabled: disabled}">\n  <a href ng-click="select()" tab-heading-transclude>{{heading}}</a>\n</li>\n')}]),angular.module("template/tabs/tabset.html",[]).run(["$templateCache",function(t){t.put("template/tabs/tabset.html",'<div>\n  <ul class="nav nav-{{type || \'tabs\'}}" ng-class="{\'nav-stacked\': vertical, \'nav-justified\': justified}" ng-transclude></ul>\n  <div class="tab-content">\n    <div class="tab-pane" \n         ng-repeat="tab in tabs" \n         ng-class="{active: tab.active}"\n         tab-content-transclude="tab">\n    </div>\n  </div>\n</div>\n')}]);