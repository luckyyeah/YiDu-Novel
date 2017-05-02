<#include "../base.ftl"/>
<#macro titleContent>  
<title>扫码支付</title>
<meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
<meta name="description" content="${getText("label.system.siteDescription")}" />
</#macro>
<style>
.p-w-hd {
    margin-bottom: 20px;
    font-size: 18px;
    font-family: "Microsoft Yahei";
    background-color: #FFFFFF;
    line-height: 1.41176471;
    padding: 10px 15px;
}
.pay-weixin, .p-w-bd {
    zoom: 1;
}
.p-w-box {
    text-align: center;
    width: 100%;
}
.pw-box-hd {
    margin-bottom: 20px;
}
.pw-box-ft {
    height: 44px;
    padding: 8px 0 8px 125px;
    background: #ff7674 url(/images/icon-red.png) 50px 8px no-repeat;
}
.pw-box-ft p {
    margin: 0;
    font-size: 20px;
    line-height: 22px;
    color: #fff;
    font-weight: bold;
}
</style>
<#macro titleContent>
    <title>用户中心</title>
    <meta name="keywords" content="${getText("label.system.siteKeywords")}"/>
    <meta name="description" content="${getText("label.system.siteDescription")}" />
    <script src="${contextPath}/themes/${themeName}/wap/js/Site.js"></script>
</#macro>

<#macro content>
	<div class="pay-weixin">
	    <div class="p-w-hd">微信扫码支付</div>
	    <div class="p-w-bd" style="position:relative">
	        <div class="p-w-box">
	            <div class="pw-box-hd">
	                <img src="${codeimgurl}" width="298">
	            </div>
	            <div class="pw-box-ft">
	                <p>长按识别图中二维码</p>
	                <p></p>
	            </div>
	        </div>
	    </div>
	</div>

    <script type="text/javascript">
        $(document).ready(function(){
            setInterval(queryOrderState, 3000);
        });

        function queryOrderState(){
         		$.get("${encodeURL("/checkorder")}", $.param({orderno: "${orderno}" })
                    , function (res) {
                        if (res.code == 0) {
                            var viplink = "/user/center";
                            location.href=viplink;
                        }else{
                        	
                        }
                    });
        }
    </script>
</#macro>