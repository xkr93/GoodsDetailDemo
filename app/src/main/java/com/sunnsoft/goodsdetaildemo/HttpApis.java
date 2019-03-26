package com.sunnsoft.goodsdetaildemo;

/**
 * Created by Administrator on 2018-09-25.
 */

public interface HttpApis {
    String HOST_API = "http://everest.isunn.cn"; //测试站
   // String HOST_API = "https://www.everestfair.com"; //正式站

    String BUYERREGISTACCOUNT = HOST_API + "/common/buyer-regist-account.htm";//APP注册接口
    String LOGINPROCESS = HOST_API + "/app/login-process";//用户登录接口
    String LOGOUT = HOST_API + "/app/logout";//退出登录接口
    String LOADUSERINFO = HOST_API + "/app/user/load-user-info.htm";//加载用户信息接口
    String IMAGEUPLOADSINGLE = HOST_API + "/common/image-upload-single.htm";//单图上传请求接口
    String IMAGEUPLOADMUTI = HOST_API + "/common/image-upload-muti.htm";//图片批量上传请求接口
    String UPDATEUSERINFO = HOST_API + "/app/user/update-user-info.htm";//修改用户个人信息接口

    String APPINDEX = HOST_API + "/app/common/app-index.htm";//APP首页接口
    String GRIDSUPPLYCOMMODITYDATA = HOST_API + "/app/common/commodity/grid-supply-commodity-data.htm";//商品列表接口
    String FINDSUPPLYCOMMODITY = HOST_API + "/app/common/commodity/find-supply-commodity.htm";//商品详情接口
    String LOADCOMMODITYDELIVERYWAYS = HOST_API + "/app/common/commodity/load-commodity-delivery-ways.htm";//加载商品配送方式接口
    String ACQUIREMATCH = HOST_API + "/app/common/commodity/acquire-match.htm";//根据所选属性值查询商品信息接口
    String GRIDCOMPANYDATA = HOST_API + "/app/common/company/grid-company-data.htm";//商家列表
    String GETCOMPANYINDEX = HOST_API + "/app/common/company/get-company-index.htm";//商家首页信息
    String GETCOMPANYRECORD = HOST_API + "/app/common/company/find-company.htm";//获取商家详情信息
    String GRIDCOMPANYCOMMENT = HOST_API + "/app/common/company/grid-company-comment.htm";//获取商家评论信息
    String GRIDCOMMODITYDATA = HOST_API + "/app/common/company/grid-commodity-data.htm";//获取商家商品列表
    String GRIDCOLLECT = HOST_API + "/app/collect/grid-collect.htm";//查询收藏产品列表接口
    String DELETECOLLECT = HOST_API + "/app/collect/delete-collect.htm";//取消收藏产品接口
    String INSERTCOLLECT = HOST_API + "/app/collect/insert-collect.htm";// 收藏商品接口
    String GRIDKINDDATA = HOST_API + "/app/common/kind/grid-kind-data.htm";//获取品类信息接口
    String GRIDSEEKPROPERTY = HOST_API + "/app/common/kind/grid-seek-property.htm";//获取品类下的搜索属性接口
    String ORDERCONFIRMPAGE = HOST_API + "/app/order/order-confirm-page.htm";//订单确认页接口
    String COMMODITYORDERDELIVERYWAYS = HOST_API + "/app/commodity/load-commodity-delivery-ways.htm";//订单确认页加载商品配送方式接口
    String AGENTORDERCONFIRMPAGE = HOST_API + "/app/agentorder/agent-order-confirm-page.htm";//代购需求订单确认页接口
    String PURCHASEORDERCONFIRMPAGE = HOST_API + "/app/purchaseorder/purchase-order-confirm-page.htm";//采购直达订单确认页接口
    String INSERTORDER = HOST_API + "/app/order/insert-order.htm";//订单下单接口-商品
    String INSERTAGENTORDER = HOST_API + "/app/agentorder/insert-agent-order.htm";//代购需求订单下单接口
    String INSERTPURCHASEORDER = HOST_API + "/app/purchaseorder/insert-purchase-order.htm";//采购需求订单下单接口
    String GRIDORDERDATA = HOST_API + "/app/order/grid-order-data.htm";//订单列表接口
    String FINDORDER = HOST_API + "/app/order/find-order.htm";//订单详情接口
    String ALIPAYFORORDER = HOST_API + "/app/order/alipay-for-order.htm";//支付宝支付商品订单
    String INSERTPROOF = HOST_API + "/app/order/insert-proof.htm";//上传支付凭证
    String ALIPAYFORWILLORDER = HOST_API + "/app/willorder/alipay-for-will-order.htm";//支付宝支付物流订单
    String WILLORDERINSERTPROOF = HOST_API + "/app/willorder/insert-proof.htm";//物流订单上传支付凭证
    String CANCELORDER = HOST_API + "/app/order/cancel-order.htm";//取消订单接口
    String CONFIRMRECEIPTORDER = HOST_API + "/app/order/confirm-receipt-order.htm";//订单确认收货接口
    String DELETEORDER = HOST_API + "/app/order/delete-order.htm";//删除订单接口
    String GRIDCOMMENT = HOST_API + "/app/order/comment/grid-comment.htm";//评价列表接口
    String INSERTCOMMENT = HOST_API + "/app/order/comment/insert-comment.htm";//订单评价接口
    String FINDCOMMENT = HOST_API + "/app/order/comment/find-comment.htm";//评价详情接口
    String ORDERAGAINBUY = HOST_API + "/app/order/order-again-buy.htm";//再次购买接口
    String GRIDCOMMODITYCOMMENT = HOST_API + "/app/common/commodity/grid-commodity-comment.htm";//获取商品评价信息接口
    String GETHOTKEY = HOST_API + "/common/get-hot-key.htm";//热搜词接口
    String GRIDADVERTISEDATA = HOST_API + "/common/advertise/grid-advertise-data.htm";//轮播图接口
    String INSERTREFUND = HOST_API + "/app/refund/insert-refund.htm";//申请退款
    String GRIDREFUND = HOST_API + "/app/refund/grid-refund.htm";//退款信息列表接口
    String FINDREFUND = HOST_API + "/app/refund/find-refund.htm";//退款信息详情接口
    String INSERTFEEDBACK = HOST_API + "/app/feedback/insert-feed-back.htm";//意见反馈接口
    String GETORDERNUM = HOST_API + "/app/order/get-order-num.htm";//订单状态对应数量接口

    String GRIDRECEIVEADDRESS = HOST_API + "/app/address/grid-receive-address.htm";//查询收货地址列表接口
    String DELETERECEIVEADDRESS = HOST_API + "/app/address/delete-receive-address.htm";//删除收货地址接口
    String UPDATERECEIVEADDRESS = HOST_API + "/app/address/update-receive-address.htm";//修改收货地址接口
    String INSERTRECEIVEADDRESS = HOST_API + "/app/address/insert-receive-address.htm";//新增收货地址接口
    String GRIDAREADATA = HOST_API + "/app/common/area/grid-area-data.htm";//获取地区列表
    String GRIDNEPALAREA = HOST_API + "/common/area/grid-nepal-area.htm";//获取尼泊尔地区列表

    String GRIDINVOICE = HOST_API + "/app/invoice/grid-invoice.htm";//发票列表接口
    String INSERTINVOICE = HOST_API + "/app/invoice/insert-invoice.htm";//新增发票
    String UPDATEINVOICE = HOST_API + "/app/invoice/update-invoice.htm";//修改发票
    String DELETEINVOICE = HOST_API + "/app/invoice/delete-invoice.htm";//删除发票

    String QUERYDELIVERYWAY = HOST_API + "/app/willservice/query-delivery-way.htm";//查询配送方式
    String QUERYSTARTPLACE = HOST_API + "/app/willservice/query-start-place.htm";//加载物流服务的出发地（物流直通车）
    String QUERYENDPLACE = HOST_API + "/app/willservice/query-end-place.htm";//查询物流服务目的地
    String QUERYDELIVERYPRICE = HOST_API + "/app/willservice/query-delivery-price.htm";//计算运费接口
    String INSERTWILLORDER = HOST_API + "/app/willorder/insert-will-order.htm";//物流订单下单接口
    String INSERTSENDINFO = HOST_API + "/app/willorder/insert-send-info.htm";//填写物流订单寄送信息
    String WILLQUERY = HOST_API + "/common/will-query.htm";//物流运输信息查询接口

    String LOADUSERCART = HOST_API + "/app/cart/load-user-cart.htm";//查询用户购物车商品
    String MODIFYUSERCART = HOST_API + "/app/cart/modify-user-cart.htm";//新增或修改购物车商品
    String DELETEUSERCART = HOST_API + "/app/cart/delete-user-cart.htm";//删除用户购物车商品
    String GETCARTNUM = HOST_API + "/app/cart/get-cart-num.htm";//查询购物车中总商品数量

    String INSERTPURCHASE = HOST_API + "/app/purchase/insert-purchase.htm";//采购直达发布接口
    String GRIDPURCHASE = HOST_API + "/app/purchase/grid-purchase.htm";//采购直达列表接口
    String FINDPURCHASE = HOST_API + "/app/purchase/find-purchase.htm";//采购直达详情接口
    String CANCELPURCHASE = HOST_API + "/app/purchase/cancel-purchase.htm";//采购直达取消接口

    String INSERTAGENT = HOST_API + "/app/agent/insert-agent.htm";//代购需求发布接口
    String GRIDAGENT = HOST_API + "/app/agent/grid-agent.htm";//代购需求列表接口
    String FINDAGENT = HOST_API + "/app/agent/find-agent.htm";//代购需求详情接口
    String CANCELAGENT = HOST_API + "/app/agent/cancel-agent.htm";//代购需求取消接口

    String GRIDWILLORDER = HOST_API + "/app/willorder/grid-will-order.htm";// 物流订单列表接口
    String FINDWILLORDER = HOST_API + "/app/willorder/find-will-order.htm";//物流订单详情接口
    String CONFIRMRECEIPTWILLORDER = HOST_API + "/app/willorder/confirm-receipt-will-order.htm";//物流订单确认收货接口
    String CANCELWILLORDER = HOST_API + "/app/willorder/cancel-will-order.htm";//取消物流订单接口
    String DELETEWILLORDER = HOST_API + "/app/willorder/delete-will-order.htm";//删除物流订单接口

    String COMPANYINSERTCOLLECT = HOST_API + "/app/companycollect/insert-collect.htm";//收藏商家接口
    String COMPANYDELETECOLLECT = HOST_API + "/app/companycollect/delete-collect.htm";//取消收藏商家接口
    String COMPANYGRIDCOLLECT = HOST_API + "/app/companycollect/grid-collect.htm";//收藏商家列表接口

    String EMAILVERIFYCODE = HOST_API + "/common/email-verify-code.htm";//邮箱验证码请求接口
    String UPUSERPASSWORDONE = HOST_API + "/app/user/up-user-password-one.htm";//修改密码接口第一步
    String UPUSERPASSWORDTWO = HOST_API + "/app/user/up-user-password-two.htm";//修改密码接口第二步
    String UPEMAILONE = HOST_API + "/app/user/up-email-one.htm";//修改邮箱接口第一步
    String UPEMAILTWO = HOST_API + "/app/user/up-email-two.htm";//修改邮箱接口第二步
    String FORGETUSERPASSWORDONE = HOST_API + "/common/user/app/forget-user-password-one.htm";//忘记密码接口第一步
    String FORGETUSERPASSWORDTWO = HOST_API + "/common/user/app/forget-user-password-two.htm";//忘记密码接口第二步

    String INSERTNOTE = HOST_API + "/app/note/insert-note.htm";//对商品或商家留言接口
    String GRIDNOTESIGNDATA = HOST_API + "/app/note/grid-note-sign-data.htm";//获取用户留言标识列表
    String GRIDNOTEDETAILDATA = HOST_API + "/app/note/grid-note-detail-data.htm";//获取用户留言详情列表
    String GRIDSYSMESSAGE = HOST_API + "/app/sysmsg/grid-sys-message.htm";//系统消息列表接口
    String FINDSYSMESSAGE = HOST_API + "/app/sysmsg/find-sys-message.htm";//系统消息详情接口


    String GRIDINFORMATION = HOST_API + "/common/information/grid-information.htm";//内容接口(新用户指南、帮助中心)
    String FINDINFORMATION = HOST_API + "/common/information/find-information.htm";//内容详情接口
    String GRIDOTHERCONTENT = HOST_API + "/common/information/grid-other-content.htm";//其他内容接口


    //敦煌支付
    String DHPAY_ORDERID = HOST_API + "/app/dhpay-page.htm?type=1&orderId=";
    String DHPAY_BELONGCODE = HOST_API + "/app/dhpay-page.htm?type=1&belongCode=";
    String DHPAY_WILLORDERID = HOST_API + "/app/dhpay-page.htm?type=2&willOrderId=";

    //分享链接
    String SHAREURL = HOST_API + "/index.html#/product-detail/";///index.html#/product-detail/215?pofferId=220
}
