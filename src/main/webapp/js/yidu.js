function form_submit(methodStr) {
	var form = $("form");
	var method = $("#method");
	method.attr("name", "method:" + methodStr);
	method.val("1");
	userFunction();
	form.submit();
	return false;
}

function userFunction() {
};

function rollOver(obj, val) {
	obj.src = val;
}

$(document).ready(function() {
	var isPopUp = $("#isPopUp");
	if (self != top && isPopUp.val() == "false") {
		window.open(self.location, '_top');
	}
	$("#submitButton").click(function() {
		$("#submitButton").attr("disabled", "disabled");
		form_submit("save");
		return false;
	});
	$("#selectAll").bind("click", selectAll);
	$("#oper_apply").click(operApply);
});

// Validate
function isHalf(chara) {
	c = chara.charCodeAt(0);
	if ((c >= 0x0 && c < 0x81) || (c == 0xf8f0) || (c >= 0xff61 && c < 0xffa0)
			|| (c >= 0xf8f1 && c < 0xf8f4)) {
		return true;
	}
	return false;
}

String.prototype.trim = function() {
	return this.replace(/^[ \n\r\t]+|[ \n\r\t]+$/g, '');
};

function customLength(str, maxLength) {
	var result = false;
	$.ajax({
		type : 'post',
		async : false,
		url : '../lengthCheck',
		data : {
			'value' : str,
			'maxLength' : maxLength
		},
		success : function(data) {
			if (data == "OK") {
				result = true;
			}
		}
	});
	return result;
}

// 文字列のbytesを取得
function getByte(text) {
	var i;
	var count = 0;
	for (i = 0; i < text.length; i++) {
		n = escape(text.charAt(i));
		if (n.length < 4) {
			count++;
		} else {
			count += 2;
		}
	}
	return count;
}

function confirmDelete(delUrl) {
	if (confirm("确认删除这条记录吗？")) {
		document.location = delUrl;
	}
}

function selectAll() {
	$("input[name='article_articleno']").attr("checked",true);
}

function unSelectAll() {
	$("input[name='article_articleno']").attr("checked",false);
}

function operApply() {
	var arrChk = $("input[name='article_articleno']:checked");
	if (arrChk.length > 0) {
		var params = "";
		$(arrChk).each(function() {
			params = params + this.value + ",";
		});
		document.location = '/admin/articleList!delete?articleNoList=' + params;
	}
}