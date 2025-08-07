package com.david.coderqa.controller;

import com.david.coderqa.wxmp.WxMpConstant;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公眾號相關接口
 *
 * @author <a href="https://github.com/giiitdavid/">David</a>

 **/
@RestController
@RequestMapping("/")
@Slf4j
public class WxMpController {

    @Resource
    private WxMpService wxMpService;

    @Resource
    private WxMpMessageRouter router;

    @PostMapping("/")
    public void receiveMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        // 校驗消息簽名，判斷是否為公眾平台發的消息
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            response.getWriter().println("非法請求");
        }
        // 加密類型
        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
                : request.getParameter("encrypt_type");
        // 明文消息
        if ("raw".equals(encryptType)) {
            return;
        }
        // aes 加密消息
        if ("aes".equals(encryptType)) {
            // 解密消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage
                    .fromEncryptedXml(request.getInputStream(), wxMpService.getWxMpConfigStorage(), timestamp,
                            nonce,
                            msgSignature);
            log.info("message content = {}", inMessage.getContent());
            // 路由消息並處理
            WxMpXmlOutMessage outMessage = router.route(inMessage);
            if (outMessage == null) {
                response.getWriter().write("");
            } else {
                response.getWriter().write(outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage()));
            }
            return;
        }
        response.getWriter().println("不可識別的加密類型");
    }

    @GetMapping("/")
    public String check(String timestamp, String nonce, String signature, String echostr) {
        log.info("check");
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        } else {
            return "";
        }
    }

    /**
     * 設置公眾號選單
     *
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/setMenu")
    public String setMenu() throws WxErrorException {
        log.info("setMenu");
        WxMenu wxMenu = new WxMenu();
        // 選單一
        WxMenuButton wxMenuButton1 = new WxMenuButton();
        wxMenuButton1.setType(MenuButtonType.VIEW);
        wxMenuButton1.setName("主選單一");
        // 子選單
        WxMenuButton wxMenuButton1SubButton1 = new WxMenuButton();
        wxMenuButton1SubButton1.setType(MenuButtonType.VIEW);
        wxMenuButton1SubButton1.setName("跳轉頁面");
        wxMenuButton1SubButton1.setUrl(
                "https://github.com/giiitdavid/");
        wxMenuButton1.setSubButtons(Collections.singletonList(wxMenuButton1SubButton1));

        // 選單二
        WxMenuButton wxMenuButton2 = new WxMenuButton();
        wxMenuButton2.setType(MenuButtonType.CLICK);
        wxMenuButton2.setName("點擊事件");
        wxMenuButton2.setKey(WxMpConstant.CLICK_MENU_KEY);

        // 選單三
        WxMenuButton wxMenuButton3 = new WxMenuButton();
        wxMenuButton3.setType(MenuButtonType.VIEW);
        wxMenuButton3.setName("主選單三");
        WxMenuButton wxMenuButton3SubButton1 = new WxMenuButton();
        wxMenuButton3SubButton1.setType(MenuButtonType.VIEW);
        wxMenuButton3SubButton1.setName("編程學習");
        wxMenuButton3SubButton1.setUrl("https://github.com/giiitdavid/");
        wxMenuButton3.setSubButtons(Collections.singletonList(wxMenuButton3SubButton1));

        // 設置主選單
        wxMenu.setButtons(Arrays.asList(wxMenuButton1, wxMenuButton2, wxMenuButton3));
        wxMpService.getMenuService().menuCreate(wxMenu);
        return "ok";
    }
}