const rootUrl = "http://localhost:8092";

$(function () {
  $("#showRegister").click(function () {
    $("#login_form").hide();
    $("#register_form").show();
    return false;
  });

  $("#showLogin").click(function () {
    $("#register_form").hide();
    $("#login_form").show();
    return false;
  });

  $("#create").click(function () {
    check_register();
    return false;
  });

  $("#login").click(function () {
    check_login();
    return false;
  });
});

function check_login() {
  var name = ""+$("#name").val();
  var password = $("#password").val();
  console.log(name);
  console.log(password);
  var url = rootUrl + "/user/login";
  if (name !== "" && password !== "") {
    // 发送登录请求
    $.ajax({
      url: url,
      type: "POST",
      data: JSON.stringify({name, password}),
      contentType: 'application/json',
      dataType: 'text',
      success: function (response) {
        const user = JSON.parse(response);

        localStorage.setItem("id", user.id);
        localStorage.setItem("name", user.name);
        localStorage.setItem("gender", user.gender)

        alert("登录成功！")
        // 跳转到主页面
        window.location.href = "homePage.html";
      },
      error: function (_xhr, _status, error) {
        alert("登录失败！用户名或密码错误！")
      }
    });
  } else {
    alert("请正确填写信息！");
  }
}

function check_register() {
  var name = $("#user_name").val();
  var password = $("#new_password").val();
  var gender = $("input[type='radio']:checked").val();
  if(gender==='男'){
    gender=1;
  }else{
    gender=0;
  }
  if (name !== "" && password !== "" && gender !== "") {
    $.ajax({
      url: rootUrl + "/user/signup",
      type: "POST",
      data: JSON.stringify({name, password, gender}),
      contentType: 'application/json',
      dataType: 'text',
      success: function (response, textStatus, jqXHR) {
        // 处理成功情况，这里的状态码通常是200
        if (jqXHR.status === 200) {
          alert("注册成功！请返回登录页面登录！");
          $("#register_form").hide();
          $("#login_form").show();
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        // 处理错误情况
        if (jqXHR.status === 409) {
          alert("用户名已存在！")
        } else {
          alert("注册失败！")
        }
      }
  });
  } else {
    alert("请正确填写信息！");
  }
}