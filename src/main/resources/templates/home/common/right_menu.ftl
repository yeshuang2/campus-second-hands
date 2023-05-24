<aside>
       
        <div id="release-button1" >
             <a  href="/home/student/publish_wanted" style="color: white" id="want-buy""> 我要买</a> 
        </div>
        <br><br>
        <div id="release-button2" >
            <a  href="/home/student/publish" style="color: white" id="want-sale"> 我要卖</a>
        </div>       
        <br><br>
        <div id="release-button3" >
            <a  href="/home/wanted_goods/list" style="color: white" > 求购专区</a>
        </div>

        <div class="helped ">
         <span class="helpers" id="order-count">0</span>
        </div>
        
        <div class="fl-wrapper clearfix">
        <div class="wx-follow">
            <a href="javascript:void(0);" class="fl-icon">
                <img src="/home/imgs/weixin-fl.png">
            </a>
            <div class="wx-fl-qrcode">
                <p>微信关注</p>
                <#if siteSetting.qrcode??>
                <img height="100px" height="100px" src="/photo/view?filename=${siteSetting.qrcode}" alt="${siteSetting.siteName!""}">
		        <#else>
                <img height="100px" height="100px" src="/home/imgs/wx-fl-qrcode.png">
                </#if>
                <p>${siteSetting.siteName!""}</p>
                <div class="dc-dot"></div>
            </div>
        </div>
       
    </div>
    </aside>
