
    $(document).ready(function() {


        //Đăng nhập
        $("#btnDangNhap").click(function () {
            $.ajax({
                url: "http://localhost:8080/api/KiemTraDangNhap",
                type: "GET",
                data: {
                    email: "hunglp52@gmail.com",
                    matkhau: "1"
                },
                success: function (value) {
                    if (value == "true")
                        alert("Dang nhap thanh cong " + value);
                    else
                        alert("Dang nhap that bai " + value);
                }
            });
        });

        $('li > a').click(function () {
            $('li').removeClass();
            $(this).parent().addClass('active');
        });

        //Thêm sản phẩm vào giỏ hàng
        $(".btn-giohang").click(function () {
            var machitietsanpham = $(this).attr("data-chitietsanpham");
            var mamau = $(this).closest("tr").find(".mau").attr("data-mau");
            var tenmau = $(this).closest("tr").find(".mau").text();
            var masize = $(this).closest("tr").find(".size").attr("data-size");
            var tensize = $(this).closest("tr").find(".size").text();
            var soluong = $(this).closest("tr").find(".soluong").text();
            var masanpham = $("#tensp").attr("data-value");
            var tensp = $("#tensp").text();
            var giatien = $("#giatien").attr("data-value");
            var hinhsanpham = $("#hinhsanpham").attr("data-value");

            $.ajax({
                url: "http://localhost:8080/mobilestore/api/add-to-cart",
                type: "GET",
                data: {
                    masanpham: masanpham,
                    masize: masize,
                    mamau: mamau,
                    tenmau: tenmau,
                    tensp: tensp,
                    tensize: tensize,
                    giatien: giatien,
                    soluong: soluong,
                    machitietsanpham:machitietsanpham,
                    hinhsanpham:hinhsanpham
                },
                success: function (value) {
                	alert("Đã thêm sản phẩm vào giỏ!");
                	 $.ajax({
                         url: "http://localhost:8080/mobilestore/detail/"+masanpham,
                         type: "GET",
                         success: function (data) {
                        	 $("#product-number-temp").html(data);
                         }
                     });
                	
                   
                	//$("#product-number-temp").setAttribute("style","height: 20px; text-align: center; position: absolute; width: 28px; font-size: 11px; border-radius: 40px; background: red; line-height: 22px; margin-top: -25px; margin-left: 10px; color: white;")
                   
                }
            });
        });

        //Tính tổng tiền của tất cả sản phẩm
        var size = 0;
        if(size > 0){
        	GanTongTien();
        }
       
        function GanTongTien(isEventChange)
        {
            var tongtien = 0;
            $(".giatien").each(function () {

                var giatien =$(this).closest("tr").find(".giatien").attr("data-value");
                var soluong =$(this).closest("tr").find(".soluonggiohang").val();


                var tongtientemp = soluong * parseFloat(giatien);
                // tongtien = tongtien +parseFloat(giatien);

                if(!isEventChange){
                   $(this).html(tongtientemp);
                }
                // tongtien = tongtien +parseFloat(giatien);
                tongtien = tongtien + tongtientemp;
                $("#tongtien").html(tongtien);
            });
        }

        //Sự kiện khi người dùng thay đổi số lượng giỏ hàng -> giá sản phẩm thay đổi
        $(".soluonggiohang").change(function () {
            var soluong = $(this).val();
            var giatien = $(this).closest("tr").find(".giatien").attr("data-value");
            var thanhtien = soluong * giatien;
            // var thanhtienformat = thanhtien.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");

            $(this).closest("tr").find(".giatien").html(thanhtien);
            GanTongTien(true);


            //Cập nhật lại số lượng giỏ hàng sau khi người dùng thay đổi số lượng trên giao diện

            var mamau = $(this).closest("tr").find(".mau").attr("data-mau");
            var masize = $(this).closest("tr").find(".size").attr("data-size");
            var masp = $(this).closest("tr").find(".sp").attr("data-masp");

            $.ajax({
                url:"http://localhost:8080/mobilestore/api/update-cart",
                type:"GET",
                data:{
                    masp:masp,
                    masize:masize,
                    mamau:mamau,
                    soluong:soluong,
                },
                success: function (value) {

                }
            });
        });

        //Xóa sản phẩm khỏi giỏ hàng
        $(".btn-delete-cart").click(function () {
            var self = $(this);
            var mamau = $(this).closest("tr").find(".mau").attr("data-mau");
            var masize = $(this).closest("tr").find(".size").attr("data-size");
            var masp = $(this).closest("tr").find(".sp").attr("data-masp");

            $.ajax({
                url:"http://localhost:8080/mobilestore/api/delete-cart",
                type:"GET",
                data:{
                    masp:masp,
                    masize:masize,
                    mamau:mamau,
                },
                success: function (value) {
                   alert(value);
                   self.closest("tr").remove();
                   $.ajax({
                       url: "http://localhost:8080/mobilestore/api/update-session-cart",
                       type: "GET",
                       success: function (data) {
                    	   size = data;
                       }
                   });
                   if(size > 0)
                	   GanTongTien(true);
                }
            });
        });
        

        //Xóa sản phẩm
        $(".btn-xoa").click(function () {
            var self = $(this);
            var masanpham = parseInt( $(this).closest("tr").find(".masanpham").attr("data-masanpham"));

                $.ajax({
                    url:"http://localhost:8080/mobilestore/api/delete-product",
                    type:"GET",
                    data:{
                        masanpham:masanpham,
                    },
                    success: function (value) {
                        self.closest("tr").remove();
                    }
                });
        })

        //SỰ kiện chọn hình ảnh
        var files =[];
        var tenhinh = "";
        $("#hinhanh").change(function (event) {
            files = event.target.files;
            tenhinh = files[0].name();

            //Tạo form lấy dữ liệu từ file, mặc định lấy file đầu tiên
            var forms = new FormData();
            forms.append("file",files[0]);

            $.ajax({
                url:"http://localhost:8080/api/UploadFile",
                type:"POST",
                data:forms,
                contentType:false,
                processData:false,
                enctype:"multipart/form-data",
                success: function (value) {

                }
            });
        });

        //Thêm chi tiết sản phẩm
        $("body").off('click').on("click",".btn-themchitiet",function (event) {

            var chitietclone = $("#chitietsanpham_clone").clone();

            chitietclone.css("display","block").appendTo($("#container-chitietsanpham"));

            event.preventDefault();
        });
        
      /* //Xóa chi tiết sản phẩm
        $("body").off('click').on("click",".btn-xoachitiet",function (event) {

        	$(this).parents()[2].setAttribute("style", "background-color: red;");
            event.preventDefault();
        });*/

        //Mở modal
        $(".btn-modal-themsanpham").click(function () {
            event.stopPropagation();
            $('.modal').modal('show');
        });

        //Lưu sản phẩm thêm mới
       $(".btn-luu").off('click').on('click',function () {
              var formdata = $("#form-sanpham").serializeArray();

              json = {};
              //json = json.toString().replaceAll("^\"|\"$|\\\\", "");
              arrChiTiet =[];

              //Set giá trị json dưới dạng name:value
              $.each(formdata,function (i,field) {
                json[field.name] = field.value;
                if(field.name =="danhmucsanpham")
                {
                    objectDanhMuc = {};
                    objectDanhMuc["madanhmuc"] = field.value;
                    json[field.name] = objectDanhMuc;
                }
              });
              //Lấy từng dòng dữ liệu gán cho objChiTiet
              //Rồi thêm vào arrChiTiet

               $("#container-chitietsanpham >.chitietsanpham").each(function () {

                   var mau = $(this).find('select[name="mau"]').val();
                   var size = $(this).find('select[name="size"]').val();
                   var soluong = $(this).find('input[name="soluong"]').val();


                   objChiTiet = {};
                   objChiTiet["mausanpham"] = mau;
                   objChiTiet["size"] = size;
                   objChiTiet["soluong"] = soluong;
                   arrChiTiet.push(objChiTiet);
               });

               //gán json = arrChiTiet
               json["chitietsanpham"] = arrChiTiet;
               json["hinhsanpham"] = tenhinh;



               //Truyền tham số json cho controller
               $.ajax({
                   url: "http://localhost:8080/mobilestore/api/add-product",
                   type: "POST",
                   data:{
                       dataJson :JSON.stringify(json)
                   },
                  
                   success: function (value) {
                	  alert("Hung Le Phi");
                   }
               });
       });

       //Sửa sản phẩm
       $(".btn-sua").off('click').on('click',function () {
           var masanpham = parseInt( $(this).closest("tr").find(".masanpham").attr("data-masanpham"));

           $.ajax({
               url:"http://localhost:8080/api/LayDanhSachSanPhamTheoMa",
               type:"POST",
               data:{
                 masanpham:masanpham,
               },

               success: function (value) {
                   console.log(value);

                   //Thông tin sản phẩm
                   $("#myModal #tensanpham").val(value.tensanpham);
                   $("#myModal #danhmucsanpham").val(value.danhMucSanPham.madanhmuc);
                   $("#myModal #giatien").val(value.giatien);
                   $("#myModal #mota").val(value.mota);
                   $("#myModal #giatien").val(value.giatien);


                   //Xóa chi tiết mặc định
                   $("#container-chitietsanpham").html("");

                   //Hiển thị chi tiết sản phẩm
                   for(i = 0; i < value.chiTietSanPhamSet.length; i++) {
                       var chitietclone = $("#chitietsanpham_clone").clone();

                       chitietclone.find("#mau").val(value.chiTietSanPhamSet[i].mau.mamau);
                       chitietclone.find("#size").val(value.chiTietSanPhamSet[i].size.masize);
                       chitietclone.find('#soluong').val(value.chiTietSanPhamSet[i].soluong);
                       chitietclone.css("display", "block").appendTo($("#container-chitietsanpham"));
                   }

                   $("#myModal").modal();
               }
           });


       })





    });
