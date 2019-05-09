var onetime=0;
//震动效果
function classnavigator(){

	if (navigator.vibrate) {
            navigator.vibrate(300);
    } else if (navigator.webkitVibrate) {
            navigator.webkitVibrate(300);
    }
}
//声音效果
var au = document.createElement("audio");
au.preload="auto";
au.src = "static/music.mp3";
function playSound() {
	if(onetime==0){
		au.play();
		classnavigator();
		//console.log(onetime);
		onetime++;
	}
	//onetime=onetime == 0 ? au.play() : 1;
}


function swt2(){
    var sHTML = [
        '<style type="text/css">',
        '.topTips { color:#000000;-webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; position: fixed; left:2%; top: 0px; width: 96%; text-align:left;  z-index: 999999999999;-webkit-perspective: 600px; perspective: 600px; }',
        '.tipsInner {font-family: "Microsoft YaHei"; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; border-radius: 8px;   -webkit-transform-origin: 0px 0px; transform-origin: 0px 0px; -webkit-transform: rotateX(90deg); transform: rotateX(90deg); }',
        '.tipsInner a { text-decoration:none;display: block; position: relative;color: #111; }',
        '.tipsInner a div img { position: absolute; left: 8px; top: 50%; margin-top: -24px; width: 45px; height: auto; padding-right: 5px; }',
        '.tipsInner dl {color:#000000;margin:0; padding: 10px 0px; }',
       '.tipsWx {background-color:#dfdfdf;opacity: 0.97; border-top-left-radius: 5px; border-top-right-radius: 5px;border-radius:12px 12px 0 0; }',
	   

	   '.tipsXx {background-color:#ccc;opacity: 0.98;border-bottom-left-radius: 5px; border-bottom-right-radius: 5px;border-radius:0 0 12px 12px; }',
		'.tipsInner dt { margin-left:8px;sline-height:1.8em; font-size:15px;}',
        '.tipsInner dd { border-radius:8px 8px 8px 8px;   background-color:#000;     opacity: 0.14;width: 39px;height: 3px;margin-left:160px; font-weight: bold;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;font-size:12px; }',
		'.tipsInner a dd img{margin-top:2px;line-height: 1.2em;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;font-size:12px; margin-left:39%;width:60px;}',
        '.showTip { visibility:visible; top:2px;}',
        '.showTip .tipsInner { -webkit-transform-origin: 0px 0px; transform-origin: 0px 0px; -webkit-transform: rotateX(0deg); transform: rotateX(0deg);}',
        '.hideTip { visibility:hidden; }',

        '.hideTip .tipsInner { -webkit-transform-origin: 0px 100%; transform-origin: 0px 100%; -webkit-transform: rotateX(-90deg); transform: rotateX(-90deg); }',
		'#LXB_CONTAINER.lxb-container{z-index: 3000;}',
        '</style>',
        '<div class="topTips" id="toptips" style="z-index: 999999999999;">',
        '   <div class="tipsInner">',
        '      	<a href="#">',
        // '         <div><img src="/static_201505/weixin.png" alt=""></div>',
        '         <dl class="tipsWx">',
        '           <img style="float:left;margin-left:7px;margin-top:-3px;width:27px;height:27px;"  alt="smile" src="/h5/wangzhuan3/images/weixin.png" width="27px" height="27px"><dt style="margin-left:42px;">新消息 <span style="float:right;margin-right:10px;">现在<span></dt>',
        '         </dl>',
		        '         <dl class="tipsXx">',
        '           <dt style="margin-left:10px;"><span style="font-weight: bold;"/>网赚团队:</span><span style="margin-left:7px;">赶紧添加屏幕下方微信快速赚钱吧！</span></dt>',
				'          	<dd style="margin-bottom:-5px;"></dd><img style="margin-top:-22px;margin-right:10px;float:right;width:23px;height:23px;display: block;"  alt="smile" src="/h5/wangzhuan3/images/smallsmile.gif" width="23px" height="23px">',
        '         </dl>',

        '       </a>',
        '   </div>',
	
        '</div>'
    ];
	sHTML=sHTML.join('\r\n');
    var o = document.createElement('div');
    o.innerHTML = sHTML;
    while (o.firstElementChild) {
        document.body.appendChild(o.firstElementChild);

    };
    T = {
        hasClass: function(d, a) {
            var c = d.className.split(/\s+/);
            for (var b = 0; b < c.length; b++) {
                if (c[b] == a) {
                    return true
                }
            }
            return false
        },
        addClass: function(b, a) {
            if (!this.hasClass(b, a)) {
                b.className += " " + a
            }
        },
        removeClass: function(d, a) {
            if (this.hasClass(d, a)) {
                var c = d.className.split(/\s+/);
                for (var b = 0; b < c.length; b++) {
                    if (c[b] == a) {
                        delete c[b]
                    }
                }
                d.className = c.join(" ")
            }
        }
    };

    function Toptips(options) {
        this.init(options);
    };

    Toptips.prototype = {

        constructor: Toptips,

        init: function(options) {
            this.item = options.item;
            this.itemInner = options.item.children[0];
            this.loop = typeof options.loop == "undefined" ? true : options.loop;
            this.showTime = typeof options.showTime == "undefined" ? 5000 : options.showTime;
            this.hideTime = typeof options.hideTime == "undefined" ? 10000 : options.hideTime;
            this.showTimer = null;
            this.hideTimer = null;
            this.preTimer = null;
            this.item.style.WebkitTransition = this.item.style.transition = this.itemInner.style.WebkitTransition = this.itemInner.style.transition = "0.5s";
            var me = this;
            var initTimer = setTimeout(function() {
                me.showTip();
            }, 3000);
        },

        showTip: function() {
            var me = this;
            T.addClass(me.item, "showTip");
            T.removeClass(me.item, "hideTip");
			playSound();
            clearTimeout(me.hideTimer);
            me.showTimer = setTimeout(function() {
                me.hideTip();
            }, me.showTime);
			//document.getElementById("top_TEL").style.display = "block";
        },

        hideTip: function() {
            var me = this;
            T.removeClass(me.item, "showTip");
            T.addClass(me.item, "hideTip");
            me.item.style.visibility = me.itemInner.style.visibility = "hidden";

            if (me.loop) {
                clearTimeout(me.showTimer);

                me.preTimer = setTimeout(function() {
                    me.item.style.visibility = me.itemInner.style.visibility = "visible";
                }, me.hideTime - 100);

                me.hideTimer = setTimeout(function() {
                    me.showTip();
                }, me.hideTime);

            }
			//document.getElementById("top_TEL").style.display = "block";
        },
		
    };
    var toptip = document.getElementById("toptips");

    new Toptips({
        item: toptip,
        loop: true
    });
    return false;
    delete o;
}
swt2();

