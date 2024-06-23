# Chat_app Android
![HeaderImage](github_images/header.png)

## Thành phần

### AndroidStudio
Android, Java

### Activity Files

- `ChatActivity.java`: Activity nhắn tin.
- `LoginOtpActivity.java`: Đăng nhập bằng OTP.
- `LoginPhoneNumberActivity.java`: Đăng nhập qua số điện thoại.
- `LoginUsernameActivity.java`: Quản lý tên tài khoản người dùng đăng nhập.
- `MainActivity.java`: Hiện lên sau giao diện loading và login, gồm các thành phần chính.
- `SearchUserActivity.java`: Cho phép người dùng tìm kiếm qua sdt và tên tài khoản.
- `LoadingScreen.java`: Hiển thị giao diện chờ.

### Fragment Files

- `ChatFragment.java`: Quản lý chat UI và các logic trong chat activity.
- `ProfileFragment.java`: Quản lý tài khoản người dùng và chỉnh sửa.
- `SearchUserFragment.java`: Hiển thị giao diện tìm kiếm.
- `SettingsFragment.java`: Cài đặt ngôn ngữ.

### Service File

- `FCMNotificationService.java`: Thêm Firebase Cloud Messaging để tạo thông báo đẩy.

### Firebase
* Authentication
* Realtime Database
* Storage
* Cloud Messaging

### Gemini Chatbot

- `BuildConfig.java`: Nơi lưu api-key.
- `ChatbotActivity.java`: Quản lý tin nhắn và nhận tin với bot.
- `GeminiProAPI.java`: Gọi API của Gemini - phiên bản pro v-1.

## Tính năng

**Start:** Đăng nhập, tạo tài khoản qua số điện thoại

**Chats_view:** Danh sách tin nhắn, thay đổi theo thời gian thực

**Profile_view:** Thay đổi ảnh đại diện, đổi tên người dùng, đăng xuất

**Settings_view:** Thay đổi cài đặt ngôn ngữ 

**Chat:** Gửi và hiển thị tin nhắn được phân loại theo timestamp

**Chatbot:** Gửi và hiển thị tin nhắn với Gemini

**Search:** Tìm bạn bè qua số điện thoại và tên tài khoản

**Etc:** Auto login, bottom navigation, notifications, progress bar...

## Giao diện - Screenshots

### Màn hình chờ | Nhập số điện thoại | Giao diện nhắn tin
<p align = "left" >
<img width="250" height="550" src="github_images/Loading_screen.png">
  |
<img width="250" height="550" src="github_images/Login_screen.png"> 
  |
<img width="250" height="550" src="github_images/Chat_view.png"> 
</p>

### Thông báo tin nhắn trong nền
<img src="github_images/Notification.png">


### Đăng nhập hoặc tạo tài khoản
<p align = "left" >
<img width="250" height="550" src="github_images/Send_Otp.png"> 
  |
<img width="250" height="550" src="github_images/Check_Otp.png"> 
  |
<img width="250" height="550" src="github_images/Username_login.png">
</p>

### Danh sách nhắn tin | Cài đặt profile | Tìm kiếm
<p align = "left" >
<img width="250" height="550" src="github_images/Recent_chat.png"> 
  |
<img width="250" height="550" src="github_images/Profile_view.png"> 
  |
<img width="250" height="550" src="github_images/Search_view.png"> 
</p>
