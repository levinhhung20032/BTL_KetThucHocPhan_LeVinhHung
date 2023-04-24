# Bài tập lớn cuối kì 2 năm học 2022-2023
## Giới thiệu
Bài làm của: Lê Vĩnh Hưng
MSV: 21011494   
Lớp: K15-KHMT   
Lớp tín chỉ: Lập trình hướng đối tượng-1-2-22(N07)

Em xin cam kết bài làm không được chia sẻ với bất kì ai ngoài giáo viên nhằm tránh hành vi sao chép bài.

Lưu ý: Bài làm được thực hiện trên IntelliJ IDEA, có thể sẽ không tương thích hoặc xảy ra lỗi khi chạy trên các IDE khác, mong thầy/cô điều chỉnh giúp em hoặc liên hệ trực tiếp với em qua mail 21011494@st.phenikaa-uni.edu.vn.

## Cấu trúc bài làm
Phần code chỉ được chứa trong thư mục "_src_"

<img width="258" alt="image" src="https://user-images.githubusercontent.com/63082008/233889517-45dde7d3-80af-4bb5-8c3a-fc5b12053338.png">

- File khởi động: ***App.java***
- Cơ sở dữ liệu: ***financial.xml***
- Các package chứa các file quản lý những hạng mục khác nhau:
	+ Package _controller_: chứa logic chính của chương trình, bao gồm logic quản lý danh sách thu/chi và logic đăng nhập cho người dùng.
	+ Package _dao_: chứa các phương thức truy cập, đọc, thêm, sửa, xóa, sắp xếp dữ liệu.
	+ Package _entity_: chứa các phương thức khởi tạo, đọc, thêm, sửa, xóa đối tượng.
	+ Package _utils_: chứa các phương thức đọc và ghi file XML.
	+ Package _view_: chứa các chương trình tạo giao diện
## Hướng dẫn sử dụng chương trình
### Khởi động
Chạy file ***App.java*** ta sẽ thế giao diện Login như sau:

<img width="290" alt="image" src="https://user-images.githubusercontent.com/63082008/233892842-65d7183b-93b1-4564-b92a-03c95a5f94c3.png">

> Vì đây là thông tin cá nhân, ta cần đăng nhập với tài khoản và mật khẩu nhằm đảm bảo tính bảo mật. Theo mặc định, tên tài khoản là "_username_"  và mật khẩu là "_password_". Nếu có mong muốn thay đổi, truy cập file ***UserDao.java***, thay đổi "username" thành **String** chứa tên tài khoản mong muốn và thay đổi "password" thành **String** chứa mật khẩu mong muốn.			<img width="421" alt="image" src="https://user-images.githubusercontent.com/63082008/233894114-485b0bbd-245b-4be6-ad1a-57d6167f8a97.png">

