<#include "base.ftl"/>



<#macro content>
    <div class="box m10" style="padding:10px 0">
        <div class="info i4" style="border-bottom:none;">
            <a href="javascript:;">
                <h3>${chaptername}</h3>
                <br/>
                <p>发布时间： <#if postdate ??>${postdate}<#else>0</#if></p>
                <p>章节字数： <#if size ??>${size}<#else>0</#if>字</p>
                <p>本章价格： <em class="price">${fee}</em>书币</p>
                <p>账户余额： <em class="price"><#if loginUser.chargefee ??>${loginUser.chargefee}<#else>0</#if></em>书币</p>
              	<#if (loginUser.chargefee> fee) >
               <a class="button blue r3" href="javascript:goPay()">购买</a>
               <#else>
                <a class="button blue r3" href="${encodeURL("/user/pay/${subDir?c}/${articleno?c}/${chapterno?c}.html")}">充值</a>
               </#if>
            </a>
        </div>
    </div>

<script type="text/javascript">
          _userno = parseInt(${loginUser.userno?c});
           _chapterno = parseInt(${chapterno?c});
           _articleno =parseInt(${articleno?c});
             _fee = parseInt(${fee?c});
					function goPay(){
									//	_chapterno=parseInt(_chapterno);
										_fee=parseInt(_fee);
                    $.get("${encodeURL("/ajaxService")}", $.param({ action: "buychapter", chapterno: _chapterno,fee:_fee,userno:_userno })
                        , function (res) {
                            if (res.code == 0) {
                            	var link = "/reader/"+Math.floor(_articleno/1000)+"/"+_articleno+"/"+_chapterno+".html";
                              location.href=link;
                            }
                        });
                   }
</script>

</#macro>

<#macro customizefooter> 
    <script type="text/javascript">


    </script>

</#macro>
