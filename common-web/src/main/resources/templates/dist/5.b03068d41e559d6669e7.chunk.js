webpackJsonp([5],{274:function(t,e,i){"use strict";function n(t){f||i(934)}Object.defineProperty(e,"__esModule",{value:!0});var r=i(506),s=i.n(r);for(var a in r)"default"!==a&&function(t){i.d(e,t,function(){return r[t]})}(a);var o=i(941),l=i.n(o),f=!1,g=i(0),p=n,h=g(s.a,l.a,!1,p,"data-v-e16990ce",null);h.options.__file="src\\views\\my-components\\split-pane\\split-pane-page.vue",e.default=h.exports},506:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=i(936),r=function(t){return t&&t.__esModule?t:{default:t}}(n);e.default={name:"split-pane-page",components:{splitPane:r.default},data:function(){return{triggerOffset:20,triggerOffsetV:70,triggerOffsetMin:40,atMax:!1,atMin:!1}},methods:{handleMousedown:function(t){this.$refs.pane.handleMousedown(t)},handleMoving:function(t){this.atMax=t.atMax,this.atMin=t.atMin}}}},507:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=i(57),r=function(t){return t&&t.__esModule?t:{default:t}}(n),s=function(t,e){return e.indexOf(t)>=0};e.default={name:"splitPane",props:{value:{type:[Number,String],default:50},direction:{type:String,default:"horizontal",validator:function(t){return s(t,["vertical","horizontal"])}},min:{type:[Number,String],default:3},max:{type:[Number,String],default:97},maxRight:{type:Boolean,default:!1},right:{type:Boolean,default:!1},triggerStyle:{type:Object,default:function(){return"horizontal"===this.direction?{width:"4px",background:"#BDBDBD"}:{height:"4px",background:"#BDBDBD"}}}},data:function(){return{prefix:"split-pane",canMove:!1,triggerOffset:50,triggerOldOffset:50,offset:{},atMin:!1,atMax:!1,directionMark:0}},computed:{wraperClasses:function(){return[this.prefix,"vertical"===this.direction?this.prefix+"-vertical":this.prefix+"-horizontal"]},leftSize:function(){return this.right?100-this.triggerOffset+"%":this.triggerOffset+"%"},rightSize:function(){return this.right?this.triggerOffset+"%":100-this.triggerOffset+"%"},triggerLeft:function(){return this.right?100-this.triggerOffset+"%":this.triggerOffset+"%"},minTransed:function(){return this.transValue(this.min)},maxTransed:function(){var t=this.right?100-this.transValue(this.max):this.transValue(this.max);return this.maxRight?100-t:t},horizontalTriggerStyle:function(){return(0,r.default)({left:this.triggerLeft},this.triggerStyle)},verticalTriggerStyle:function(){return(0,r.default)({top:this.triggerLeft},this.triggerStyle)}},methods:{handleMouseup:function(t){this.canMove=!1,this.$emit("on-resizing-end",t)},transValue:function(t){return"number"==typeof t?t:Math.floor(parseFloat(t)/this.$refs.wraper.offsetWidth*1e4)/100},handleMousedown:function(t){this.canMove=!0,this.triggerOldOffset=this.triggerOffset,this.offset={x:t.pageX,y:t.pageY},this.$emit("on-resizing-start",t),t.preventDefault()},handleMouseout:function(){this.canMove=!1},handleMousemove:function(t){if(this.canMove){var e=void 0,i=0;if("horizontal"===this.direction?(i=Math.floor((t.clientX-this.offset.x)/this.$refs.wraper.offsetWidth*1e4)/100,e=this.triggerOldOffset+(this.right?-i:i)):(i=Math.floor((t.clientY-this.offset.y)/this.$refs.wraper.offsetHeight*1e4)/100,e=this.triggerOldOffset+(this.right?-i:i)),this.right){var n=100-e;n<=this.minTransed?this.triggerOffset=100-Math.max(n,this.minTransed):this.triggerOffset=100-Math.min(n,this.maxTransed)}else e<=this.minTransed?this.triggerOffset=Math.max(e,this.minTransed):this.triggerOffset=Math.min(e,this.maxTransed);t.atMin=100-e<=this.minTransed,t.atMax=100-e>=this.maxTransed,t.pageX>this.directionMark?t.direction=1:t.direction=0,this.directionMark=t.pageX,this.$emit("input",this.triggerOffset),this.$emit("on-resizing",t)}},setTriggerOffset:function(t){var e=this;this.$nextTick(function(){e.triggerOffset="number"==typeof t?t:Math.floor(parseInt(t)/e.$refs.wraper.offsetWidth*1e4)/100,e.$emit("input",e.triggerOffset)})}},watch:{value:function(t){var e=this;this.$nextTick(function(){e.triggerOffset="number"==typeof t?t:Math.floor(parseInt(t)/e.$refs.wraper.offsetWidth*1e4)/100})}},mounted:function(){var t=this;void 0!==this.value&&(this.$nextTick(function(){t.triggerOffset="number"==typeof t.value?t.value:Math.floor(parseInt(t.value)/t.$refs.wraper.offsetWidth*1e4)/100}),this.triggerOffset="number"==typeof this.value?this.value:Math.floor(parseInt(this.value)/this.$refs.wraper.offsetWidth*1e4)/100)}}},934:function(t,e,i){var n=i(935);"string"==typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);i(11)("073b586b",n,!1)},935:function(t,e,i){e=t.exports=i(10)(!1),e.push([t.i,"\n.split-pane-con[data-v-e16990ce] {\n  width: 100%;\n  height: 89vh;\n}\n.custom-trigger[data-v-e16990ce] {\n  position: absolute;\n  width: 40px;\n  height: 40px;\n  box-sizing: border-box;\n  top: 50%;\n  -webkit-transform: translate(-50%, -50%);\n          transform: translate(-50%, -50%);\n  background: white;\n  border-radius: 50%;\n  box-shadow: 2px 2px 5px 2px rgba(0, 0, 0, 0.1), 2px 2px 10px 2px rgba(0, 0, 0, 0.2) inset;\n  border: 1px solid #c3c3c3;\n  cursor: pointer;\n}\n.introduce-left-con h4[data-v-e16990ce] {\n  margin-bottom: 20px;\n}\n.introduce-left-con h5[data-v-e16990ce] {\n  margin-bottom: 10px;\n  margin-left: 20px;\n}\n.split-pane-right-con[data-v-e16990ce] {\n  padding: 30px;\n}\n.split-pane-right-con p[data-v-e16990ce] {\n  font-size: 26px;\n  font-weight: 700;\n  color: white;\n}\n",""])},936:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=i(937),r=function(t){return t&&t.__esModule?t:{default:t}}(n);e.default=r.default},937:function(t,e,i){"use strict";function n(t){f||i(938)}Object.defineProperty(e,"__esModule",{value:!0});var r=i(507),s=i.n(r);for(var a in r)"default"!==a&&function(t){i.d(e,t,function(){return r[t]})}(a);var o=i(940),l=i.n(o),f=!1,g=i(0),p=n,h=g(s.a,l.a,!1,p,null,null);h.options.__file="src\\views\\my-components\\split-pane\\split-pane\\split-pane.vue",e.default=h.exports},938:function(t,e,i){var n=i(939);"string"==typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);i(11)("3d535f80",n,!1)},939:function(t,e,i){e=t.exports=i(10)(!1),e.push([t.i,"\n.split-pane {\n  position: relative;\n}\n.split-pane-container {\n  height: 100%;\n  width: 100%;\n}\n.split-pane-horizontal > div > .split-pane-trigger {\n  -webkit-transform: translateX(-50%);\n          transform: translateX(-50%);\n  cursor: col-resize;\n  width: 8px;\n  height: 100%;\n  margin: 0 1px;\n}\n.split-pane-horizontal > div > .split-pane-trigger .trigger-middle-point {\n  width: 3px;\n  height: 20px;\n}\n.split-pane-horizontal > div > .split-pane-trigger .trigger-middle-point p {\n  width: 100%;\n  height: 1px;\n  margin-top: 2px;\n}\n.split-pane-vertical > div > .split-pane-trigger {\n  -webkit-transform: translateY(-50%);\n          transform: translateY(-50%);\n  cursor: row-resize;\n  height: 8px;\n  width: 100%;\n  margin: 1px 0;\n}\n.split-pane-vertical > div > .split-pane-trigger .trigger-middle-point {\n  width: 20px;\n  height: 3px;\n}\n.split-pane-vertical > div > .split-pane-trigger .trigger-middle-point p {\n  height: 100%;\n  width: 1px;\n  display: inline-block;\n  margin-left: 2px;\n}\n.split-pane-trigger {\n  position: absolute;\n  z-index: 3;\n  background: #F8F8F9;\n  box-shadow: 0 0 4px 0 rgba(28, 36, 56, 0.32);\n}\n.split-pane-trigger .trigger-middle-point {\n  position: absolute;\n  top: 50%;\n  left: 50%;\n  -webkit-transform: translate(-50%, -87%);\n          transform: translate(-50%, -87%);\n  line-height: 0px;\n}\n.split-pane-trigger .trigger-middle-point p {\n  background: rgba(23, 35, 61, 0.25);\n}\n.split-pane-left-area {\n  height: 100%;\n  float: left;\n  z-index: 2;\n}\n.split-pane-right-area {\n  height: 100%;\n  float: left;\n  z-index: 2;\n}\n.split-pane-top-area {\n  width: 100%;\n  z-index: 2;\n}\n.split-pane-bottom-area {\n  width: 100%;\n  z-index: 2;\n}\n",""])},940:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{ref:"wraper",class:t.wraperClasses,on:{mouseup:t.handleMouseup,mousemove:t.handleMousemove,mouseleave:t.handleMouseout}},["horizontal"===t.direction?i("div",{class:t.prefix+"-container"},[i("div",{class:t.prefix+"-left-area",style:{width:t.leftSize}},[t._t("left")],2),t._v(" "),t._t("trigger",[i("div",{ref:"trigger",class:t.prefix+"-trigger",style:t.horizontalTriggerStyle,attrs:{unselectable:"on"},on:{mousedown:t.handleMousedown}},[t._m(0)])]),t._v(" "),i("div",{class:t.prefix+"-right-area",style:{width:t.rightSize}},[t._t("right")],2)],2):i("div",{class:t.prefix+"-container"},[i("div",{class:t.prefix+"-top-area",style:{height:t.leftSize}},[t._t("top")],2),t._v(" "),t._t("trigger",[i("div",{ref:"trigger",class:t.prefix+"-trigger",style:t.verticalTriggerStyle,attrs:{unselectable:"on"},on:{mousedown:t.handleMousedown}},[t._m(1)])]),t._v(" "),i("div",{class:t.prefix+"-bottom-area",style:{height:t.rightSize}},[t._t("bottom")],2)],2)])},r=[function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"trigger-middle-point"},[i("p"),i("p"),i("p"),i("p"),i("p"),i("p")])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"trigger-middle-point"},[i("p"),i("p"),i("p"),i("p"),i("p"),i("p")])}];n._withStripped=!0;var s={render:n,staticRenderFns:r};e.default=s},941:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("Card",{attrs:{padding:0}},[i("div",{staticClass:"split-pane-con"},[i("split-pane",{style:{height:"100%"},attrs:{right:"",min:20,max:"100px",direction:"horizontal"},on:{"on-trigger-moving":t.handleMoving},model:{value:t.triggerOffset,callback:function(e){t.triggerOffset=e},expression:"triggerOffset"}},[i("div",{staticStyle:{height:"100%"},attrs:{slot:"left"},slot:"left"},[i("split-pane",{style:{height:"100%"},attrs:{direction:"vertical"},model:{value:t.triggerOffsetV,callback:function(e){t.triggerOffsetV=e},expression:"triggerOffsetV"}},[i("div",{staticClass:"introduce-left-con",staticStyle:{background:"#EDE3A0",height:"100%",padding:"30px"},attrs:{slot:"top"},slot:"top"},[i("h4",[t._v("- 该组件可以拖动修改左右尺寸，还可以绑定v-model来设置，如设置v-model=\"40\"即左侧40%，右侧60%，也可设置'200px'像素值")]),t._v(" "),i("h4",[t._v("- 设置right属性则v-model设置的值为右侧（下册）的宽度（高度）")]),t._v(" "),i("h4",[t._v('- 可设置最小和最大距离，如:min="80"即向右拖动到80%处就不能再拖动')]),t._v(" "),i("h4",[t._v("- 可绑定事件@on-trigger-moving，回调函数的返回值是鼠标事件对象，同时该对象还包括两个我们自定义的变量，即atMax和atMin，即此时是否是在最大或最小距离处，类型是Boolean。来拖动右边的trigger看看吧。")]),t._v(" "),i("h4",{staticStyle:{"margin-bottom":"10px"}},[t._v('- 可使用slot="trigger"自定义拖动触发器，但有三个注意点:')]),t._v(" "),i("h5",[t._v("-- 样式需要设置position: absolute;")]),t._v(" "),i("h5",[t._v("-- 需要给trigger绑定mousedown事件，绑定的方法调用this.$refs.pane.handleMousedow(e)，e为mousedown事件的事件对象")]),t._v(" "),i("h5",[t._v("-- 给trigger添加:style=\"{width: offset + '%'}\"，这里的offset是通过v-model给split-pane组件绑定的值")]),t._v(" "),i("h4",[t._v("- 其他api请看源码")])]),t._v(" "),i("div",{staticStyle:{background:"#A2EDB6",height:"100%"},attrs:{slot:"bottom"},slot:"bottom"},[i("split-pane",{ref:"pane",style:{height:"100%"},attrs:{direction:"horizontal"},model:{value:t.triggerOffsetMin,callback:function(e){t.triggerOffsetMin=e},expression:"triggerOffsetMin"}},[i("div",{staticStyle:{background:"#EDACE2",height:"100%"},attrs:{slot:"left"},slot:"left"}),t._v(" "),i("div",{staticClass:"custom-trigger",style:{left:t.triggerOffsetMin+"%"},attrs:{slot:"trigger"},on:{mousedown:t.handleMousedown},slot:"trigger"}),t._v(" "),i("div",{staticStyle:{background:"#A2EDB6",height:"100%"},attrs:{slot:"right"},slot:"right"})])],1)])],1),t._v(" "),i("div",{staticClass:"split-pane-right-con",staticStyle:{background:"#8FB5ED",height:"100%"},attrs:{slot:"right"},slot:"right"},[i("p",[t._v("是否是在最小距离处： "+t._s(t.atMin))]),t._v(" "),i("p",[t._v("是否是在最大距离处： "+t._s(t.atMax))])])])],1)])],1)},r=[];n._withStripped=!0;var s={render:n,staticRenderFns:r};e.default=s}});