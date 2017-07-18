// NotebookAidlInterface.aidl
package com.punuo.sys.app;

// Declare any non-default types here with import statements
import com.punuo.sys.app.model.MailInfo;
import com.punuo.sys.app.model.Friend;
import com.punuo.sys.app.model.UserInfo;
interface NotebookAidlInterface {
    List<MailInfo> getMailInfo();
    List<Friend> getFriendList();
    UserInfo getUserInfo();
    void sendMail(String mailId,String fromId,String toId,String theme,String content);
    void deleteMail(String mailId);
}
