//获得鼠标坐标值
function mouseCoords(e) {
	var e = window.event || e;
	if (e.pageX || e.pageY) {
		return {
			x : e.pageX,
			y : e.pageY
		};
	}
	return {
		x : e.clientX + document.body.scrollLeft - document.body.clientLeft,
		y : e.clientY + document.body.scrollTop - document.body.clientTop
	};
	return false;
}

function ForDight(Dight, How) {
	Dight = Math.round(Dight * Math.pow(10, How)) / Math.pow(10, How);
	return Dight;
}

var timer;
function StopScroll() {
	if (timer != '') {
		clearInterval(timer);
	}
	if ($("#mousebox").css('display') != 'none') {
		$("#mousebox").css('display', 'none');
	}
}

function BeginScroll() {
	if ($.cookie("axyx_speed") != null) {
		timer = setInterval("Scrolling()", $.cookie("axyx_speed"));
	} else {
		timer = setInterval("Scrolling()", 50);
	}
}

function setSpeed(o) {
	$.cookie("axyx_speed", o, {expires : 365,path : '/'});
	$("#sudu a").removeClass("this");
	$("#sudu" + $.cookie("axyx_speed")).addClass("this");
}

function changeSpeedStyle(){
	
}

function Scrolling() {
	var currentpos = 1;
	if (navigator.userAgent.toLowerCase().match(/chrome/) != null) {
		currentpos = document.body.scrollTop;
	} else {
		currentpos = document.documentElement.scrollTop;
	}
	console.log(currentpos);
	window.scroll(0, ++currentpos);
	if (navigator.userAgent.toLowerCase().match(/chrome/) != null) {
		temPos = document.body.scrollTop;
	} else {
		temPos = document.documentElement.scrollTop;
	}

	if (currentpos != temPos) {
		clearInterval(timer);
	}
}

function setBG(o) {
	$("#content").css('backgroundColor', o);
	$.cookie("axyx_background", o, {	expires : 365,	path : '/'});
}

function setFontColor(o) {
	$("#content").css("color", o);
	$.cookie("axyx_fontColor", o, {	expires : 365,	path : '/'});
}

function setFontSize(size) {
	$("#content").css("fontSize", size);
	$.cookie("axyx_fontSize", size, {expires : 365,	path : '/'});
}

$(document).ready(function(){
	//替换关键字
	replaceKeyWord();
	//保存阅读履历
	saveReadHistory();
	//设置阅读页滚动
	document.onclick = StopScroll;
	document.ondblclick = BeginScroll;
	//设置阅读页样式
	setFont();
 });

//替换章节内容中的关键字
function replaceKeyWord(){
	var str = $("#content").html();
	str = str.replace(/\<script[\s\S]+?\<\/script\>/gi, "");
	str = str.replace(/\<styltyp[\s\S]+?\<\/styl\>/gi, "");
	str = str.replace(/\<style[\s\S]+?\<\/style\>/gi, "");
//	str = str.replace(/\<a[\s\S].+?\<\/a\>/gi, "");
	str = str.replace(/Www.+?ggyy\.net/gi, "");
	str = str.replace(/Www.+?Com/gi, "");
	str = str.replace(/Www.+?net/gi, "");
	str = str.replace(/Www.+?cc/gi, "");
	str = str.replace(/&lt;br.+?&gt;/gi, "<br />");
	str = str.replace(/&amp;hllp;/gi, "&hellip;").replace(/&amp;ldqo;/gi,
			"&ldquo;").replace(/ldqo/gi, "ldquo").replace(/&amp;rdqo;/gi,
			"&rdquo;").replace(/&amp;dash;/gi, "&mdash;");
	$("#content").html(str);
}

var readHistoryObject = new Object();
//保存阅读履历
function saveReadHistory(){
	//save read history
    var readhistory = $.cookie("readhistory");
    if(! readhistory ){
         readhistory = new Array();
    }else{
         readhistory = JSON.parse(readhistory);
    }
    var index = readHistoryObject.articleno.in_array(readhistory);
    if(index != -1){
         readhistory.splice(index,1);
    }
    readhistory.splice(0,0,readHistoryObject);
    if(readhistory.length > 10 ){
         readhistory.splice(9,readhistory.length - 10);
    }
    $.cookie("readhistory",JSON.stringify(readhistory),{path:'/' ,expires: 365});
}
//翻页功能
 function jumpPage() {
          var event = document.all ? window.event : arguments[0];
          if (event.keyCode == 37) document.location = preview_page;
          if (event.keyCode == 39) document.location = next_page;
}
 
 //阅读页格式设置
 function setFont(){

	if ($.cookie("axyx_background")) {
		setBG($.cookie("axyx_background"));
	}
	if ($.cookie("axyx_fontColor")) {
		setFontColor($.cookie("axyx_fontColor"));
		$("#txtcolor").val($.cookie("axyx_fontColor"));
	}
	if ($.cookie("axyx_fontSize")) {
		setFontSize($.cookie("axyx_fontSize"));
	} else {
		$("#content").css({fontSize : "16pt"});
	}
	if ($.cookie("axyx_speed")) {
		$("#sudu a").removeClass("this");
		$("#sudu" + $.cookie("axyx_speed")).addClass("this");
	}
 }
 