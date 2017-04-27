/// <reference path="jQuery.Min.js" />

(function () {

    window.Util = {

        Alert: function (msg, callback) {

            var htm = [
                '<div id="_jxalertbox" class="alertbox">',
                '    <div class="alert-div"><p>' + msg + '</p></div>',
                '</div>'
            ];

            $("#_jxalertbox").remove();
            $(htm.join("")).appendTo("body");

            var box = $("#_jxalertbox");
            box.css({ top: ($(window).height() - box.height() - 20 + $(window).scrollTop()) + "px" });
            
            setTimeout(function () {
                $("#_jxalertbox").remove();
                if (typeof callback == "function") {
                    callback();
                }
            }, 2000);

        },

        Loading: function (msg) {
            var htm = [
                '<div id="_jxloadingbox" class="loadingbox">',
                '    <div class="loading-div">',
                '    </div>',
                '</div>'
            ];
            $("#_jxloadingbox").remove();
            $(htm.join("")).appendTo("body");
            var lbox = $("#_jxloadingbox");
            lbox.css({ top: ($(window).height() - lbox.height()) * 0.4 + $(window).scrollTop() + "px" });
        },

        LoadingClear: function () {
            $("#_jxloadingbox").remove();
        },

        _Cookie: function (name, value, options) {

            if (typeof value != 'undefined') {

                options = options || {};
                if (value === null) {
                    value = '';
                    options.expires = -1;
                }

                var expires = '';

                if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
                    var date;
                    if (typeof options.expires == 'number') {
                        date = new Date();
                        date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
                    } else {
                        date = options.expires;
                    }
                    expires = '; expires=' + date.toUTCString();
                }
                var path = options.path ? '; path=' + (options.path) : '';
                var domain = options.domain ? '; domain=' + (options.domain) : '';
                var secure = options.secure ? '; secure' : '';
                document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
            } else {
                var cookieValue = '';
                if (document.cookie && document.cookie != '') {
                    var cookies = document.cookie.split(';');
                    for (var i = 0; i < cookies.length; i++) {
                        cookie = cookies[i].replace(/^\s+|\s+$/g, '');
                        if (cookie.substring(0, name.length + 1) == (name + '=')) {
                            cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                            break;
                        }
                    }
                }
                return cookieValue;
            }
        },

        CookieValue: function (cookieName) {
            return this._Cookie(cookieName);
        },

        CookieWrite: function (name , value , expires) {
            this._Cookie(name, value, { path: '/', expires: expires });
        },

        NumberFormat: function (number) {
            var number = parseInt(number);
            if (number > 10000) return parseInt(number / 10000) + "万";
            return number + "";
        },

        goBack : function () {          
            if (document.referrer == "") {
                location.href = "index";
                return;
            }
            history.go(-1);
        }
    };

    /*移除数组元素 @index:数组索引*/
    Array.prototype.remove = function (index) {
        if (index < 0) return this;
        return this.slice(0, index).concat(this.slice(index + 1, this.length));
    }

    

})();
