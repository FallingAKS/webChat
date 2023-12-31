const rootUrl = "http://localhost:8092";

const id = parseInt(localStorage.getItem("id"));
let isGerenzhongxin = false;
let isFriend=true;

// 定义一个函数来格式化个位数为两位数
function padTo2Digits(num) {
  return num.toString().padStart(2, '0');
}

// 定义一个函数来构建格式化的日期时间字符串
function formatDate(timestamp) {
  const date = new Date(timestamp);
  return [
    date.getFullYear(),
    '年',
    padTo2Digits(date.getMonth() + 1), // 月份是从0开始的
    '月',
    padTo2Digits(date.getDate()),
    '日 ',
    padTo2Digits(date.getHours()),
    ':',
    padTo2Digits(date.getMinutes()),
    ':',
    padTo2Digits(date.getSeconds())
  ].join('');
}

// 获取用户好友列表函数
async function getFriendList() {
  try {
    return await $.ajax({
      url: rootUrl + "/friend/user/" + id,
      type: "GET",
      dataType: 'json'
    });
  } catch (error) {
    alert("用户好友列表获取失败！");
    throw error; // 抛出错误，如果需要的话可以在外部捕获
  }
}

async function getGroupList() {
  try {
    return await $.ajax({
      url: rootUrl + "/take/user/" + id,
      type: "GET",
      dataType: 'json'
    });
  } catch (error) {
    alert("用户群列表获取失败！");
    throw error; // 抛出错误，如果需要的话可以在外部捕获
  }
}

let friendList = [];
let friendListItems = [];

let groupList = [];
let groupListItems = [];
let messageList = [];

// 使用async/await
function promiseGetFriendList() {
  (async () => {
    try {
      const newFriendList = await getFriendList();
      if (newFriendList.length === friendList.length) {
        $('.chatList').empty();
        for (let i = 0; i < friendListItems.length; i++) {
          $('.chatList').append(friendListItems[i]);
        }
        return;
      }
      friendList = newFriendList;
      $('.chatList').empty();
      friendListItems = [];

      for (let i = 0; i < friendList.length; i++) {
        let friendId = friendList[i];

        // 获取好友信息
        let friendInfo = await $.ajax({
          url: rootUrl + "/user/" + friendId,
          type: "GET",
          dataType: 'json'
        });

        let avatar = (friendInfo.gender === 1)?"./images/male.png":"./images/female.png";

        // 添加好友到好友列表
        let friendListItem = '<div class="block" data-id=' +
          friendInfo.id +
          '>' +
          '<div class="imgbx">' +
          '<img src="' + avatar +
          '" alt="" class="cover">' +
          '</div>' +
          '<div class="details">' +
          '<div class="listHead">' +
          '<H4>' +
          friendInfo.name +
          '</H4>' +
          '<button class="deleteFriend" data-id=' +
          friendInfo.id +
          '>删除好友</button>' +
          // '<p class="time">10:56</p>'+
          '</div>' +
          '</div>' +
          '</div>';
        $('.chatList').append(friendListItem);
        friendListItems.push(friendListItem);
      }
    } catch (error) {
      console.error("An error occurred:", error);
    }
  })();
}
// 使用async/await
function promiseGetGroupList() {
  (async () => {
    try {
      const newGroupList = await getGroupList();
      if (newGroupList.length === groupList.length) {
        $('.chatList').empty();
        for (let i = 0; i < groupListItems.length; i++) {
          $('.chatList').append(groupListItems[i]);
        }
        return;
      }
      groupList = newGroupList;
      console.log(groupList);
      $('.chatList').empty();
      groupListItems = [];

      for (let i = 0; i < groupList.length; i++) {
        let groupId = groupList[i];

        // 获取好友信息
        let groupInfo = await $.ajax({
          url: rootUrl + "/group/" + groupId,
          type: "GET",
          dataType: 'json'
        });

        // 添加好友到好友列表
        const group = '<div class="blockGroup" data-id=' +
          groupInfo.groupId +
          '>' +
          '<div class="imgbx">' +
          '<img src="./images/heart.png" alt="" class="cover">' +
          '</div>' +
          '<div class="details">' +
          '<div class="listHead">' +
          '<H4>' +
          groupInfo.groupName +
          '</H4>' +
          // '<p class="time">10:56</p>'+
          '</div>' +
          '</div>' +
          '</div>';
        $('.chatList').append(group);
        groupListItems.push(group);
      }
    } catch (error) {
      console.error("An error occurred:", error);
    }
  })();
}

promiseGetFriendList();

$('#submitFriend').click(function() {
  // 获取输入的好友名
  var friendName = $('#friendName').val();
  var userId = id;
  console.log(friendName);
  console.log(userId);

  // 发送Ajax请求
  $.ajax({
    url: rootUrl + '/friend',
    type: 'POST',
    data: {
      userId: userId,
      friendName: friendName
    },
    dataType: 'json',
    success: function(response) {
      // 请求成功后的回调函数
      alert('添加成功！');

      promiseGetFriendList();
    },
    error: function(xhr, status, error) {
      // 400
      if (xhr.status === 404) {
        alert('添加失败！该用户不存在！');
      }else if (xhr.status === 400) {
        alert('添加失败！该用户已经是您的好友！');
      }else{
        alert('添加失败！')
      }
    }
  });
});

$('#haoyou').click(function () {
  isGerenzhongxin = false;
  $('#IconSelect').attr("id", "haoyou");
  $(this).attr("id", "IconSelect");
  promiseGetFriendList();
})

$('#group').click(function () {
  isGerenzhongxin = false;
  $('#IconSelect').attr("id", "group");
  $(this).attr("id", "IconSelect");
  promiseGetGroupList();
})

$('#gerenzhongxin').click(function () {
  isGerenzhongxin = true;
  $('#IconSelect').attr("id", "gerenzhongxin");
  $(this).attr("id", "IconSelect");
  $('.chatBox').empty();
  var i = '';
  i += '<div class="changeInformationBox">' +
    '<div>' +
    '<input type="text" placeholder="请输入新的昵称" class="newName">' +
    '</div>' +
    '<br>' +
    '<div>' +
    '    <input type="text" placeholder="请输入新的密码" id="" class="newPassword">' +
    '</div>' +
    '<br>' +
    '        <div class="gender-options">\n' +
    '          <span class="gender-label" style="min-width: 80px;">性别： </span>\n' +
    '          <input type="radio" name="sex" id="male" value="男" checked style="max-width: 50px;">\n' +
    '          <label for="male" style="max-width: 50px;">男</label>\n' +
    '          <span class="gender-label" style="min-width: 20px;"></span>\n' +
    '          <input type="radio" name="sex" id="female" value="女" style="max-width: 50px;">\n' +
    '          <label for="female" style="max-width: 50px;">女</label>\n' +
    '        </div>'+
    '<div>' +
    '<input type="text" placeholder="请输入新的个性签名" id="" class="newSentence">' +
    '</div>' +
    '</div>' +
    '<div class="newInformationBox">' +
    '<button class="submitNewInformation">' +
    '<span>提交</span>' +
    '</button>' +
    '</div>';
  $('.chatBox').append(i);
})

$(document).on('click', '.submitNewInformation', function () {
  var newName = $('.newName').val();
  var newPassword = $('.newPassword').val();
  var newGender = $("input[type='radio']:checked").val();
  if(newGender==='男'){
    newGender=1;
  }else{
    newGender=0;
  }
  var newSentence = $('.newSentence').val();
  console.log(newName, newPassword, newGender, newSentence);
  $.ajax({
    url: rootUrl + "/user",
    type: "PUT",
    data: JSON.stringify({
      id: id,
      name: newName,
      gender: newGender,
      password: newPassword,
      signature: newSentence
    }),
    contentType: 'application/json',
    dataType: 'text',
    async: false,
    success: function (res) {
      // $('.chatList').empty();
      alert("修改成功！")

    },
    error: function (jqXHR, textStatus, errorThrown) {
      // 处理错误情况
      if (jqXHR.status === 400) {
        alert("用户名已存在！")
      } else {
        alert("修改失败！")
      }
    }
  })
})

$("#btn-send-message").click(function () {
  var otherID = localStorage.getItem("otherID");
  var otherGroupID = localStorage.getItem("otherGroupID");
  var user_input_area = $("#user-input-value").val();
  $("#user-input-value").val("");
  console.log(user_input_area);
  if (user_input_area.length !== 0) {
    if (isFriend) {
      $.ajax({
        url: rootUrl + "/message",
        type: "POST",
        data: JSON.stringify({
          "msgContent": user_input_area,
          "msgFrom": id,
          "msgTo": otherID
        }),
        contentType: 'application/json',
        dataType: 'json',
        success: function (res) {
          $(".chatBox").append(my_message_html(user_input_area));
          console.log(res);
        },
        error: function () {
          alert("发送消息失败！")
        }
      })
    } else {
      $.ajax({
        url: rootUrl + "/groupMessage",
        type: "POST",
        data: JSON.stringify({
          "groupMsgContent": user_input_area,
          "groupMsgFrom": id,
          "groupId": otherGroupID
        }),
        contentType: 'application/json',
        dataType: 'json',
        success: function (res) {
          $(".chatBox").append(my_message_html(user_input_area));
          console.log(res);
        },
        error: function () {
          alert("发送消息失败！")
        }
      })
    }
  }
});

function my_message_html(incoming_message) {
  const time = new Date();
  let year = time.getFullYear();
  let month = time.getMonth() + 1;
  month = month < 10 ? ('0' + month) : month;
  let day = time.getDate();
  day = day < 10 ? ('0' + day) : day;
  let hours = time.getHours();
  hours = hours < 10 ? ('0' + hours) : hours;
  let mins = time.getMinutes();
  mins = mins < 10 ? ('0' + mins) : mins;
  let seconds = time.getSeconds();
  seconds = seconds < 10 ? ('0' + seconds) : seconds;
  return '<div class="message my_message">' +
    '<p>' + incoming_message + '<br>' +
    '<span>' + year + '年' + month + '月' + day + '日 ' + hours + ':' + mins + ':' + seconds +
    '</span></p>' +
    '</div>';
}

function getMessages() {
  $.ajax({
    url: rootUrl + "/message/user",
    type: "GET",
    data: {
      userId: id,
      friendId: localStorage.getItem("otherID")
    },
    dataType: 'json',
    success: function (res) {
      if (localStorage.getItem("otherID") === localStorage.getItem("oldOtherID") && res.length === messageList.length) {
        // for (let i = 0; i < messageList.length; i++) {
        //   $('.chatBox').append(messageList[i]);
        // }
        return;
      }
      $(".chatBox").empty();
      for (let i = 0; i < res.length; i++) {
        let l = '';
        if (res[i].msgFrom === id) {
          l += '<div class="message my_message" data-id=' +
            res[i].msgId +
            '>' +
            '<span class="operation">' +
            '<button class="chehui" data-id=' +
            res[i].msgId +
            '>撤回</button>' +
            '</span>' +
            '<p>' +
            res[i].msgContent +
            '<br><span>' +
            formatDate(res[i].msgTime) +
            '</span></p>' +
            '</div>';
        } else {
          l += '<div class="message friend_message" data-id=' +
            res[i].msgId +
            '   >' +
            '<p>' +
            res[i].msgContent +
            '<br><span>' +
            formatDate(res[i].msgTime) +
            '</span></p>' +
            '</div>';
        }
        $(".chatBox").append(l);
        messageList.push(l);
        localStorage.setItem("oldOtherID", localStorage.getItem("otherID"));
      }
    },
    error: function () {
      alert("获取聊天记录失败！")
    }
  })
}

function getGroupMessages() {
  $.ajax({
    url: rootUrl + "/groupMessage/group",
    type: "GET",
    data: {
      groupId: localStorage.getItem("otherGroupID")
    },
    dataType: 'json',
    success: function (res) {
      if (localStorage.getItem("otherGroupID") === localStorage.getItem("oldOtherGroupID") && res.length === messageList.length) {
        // for (let i = 0; i < messageList.length; i++) {
        //   $('.chatBox').append(messageList[i]);
        // }
        return;
      }
      $(".chatBox").empty();
      let starMessage = '';
      starMessage += '<div style="height: 30px; background: yellow;">' +
        '<span>精华消息：' + localStorage.getItem("groupStarMessage") +
        '</span>' +
        '</div>';
      $(".chatBox").append(starMessage);
      for (var i = 0; i < res.length; i++) {
        var l = '';
        if (res[i].groupMsgFrom === id) {
          l += '<div class="message my_message" data-id=' +
            res[i].groupMsgId +
            '>' +
            '<span class="operation">' +
            '<button class="chehui" data-id=' +
            res[i].groupMsgId +
            '>撤回</button>' +
            '</span>' +
            '<p>' +
            res[i].groupMsgContent +
            '<br><span>' +
            formatDate(res[i].groupMsgTime) +
            '</span></p>' +
            '</div>';
        } else {
          l += '<div class="message friend_message" data-id=' +
            res[i].groupMsgId +
            '   >' +
            '<p>' +
            res[i].groupMsgContent +
            '<br><span>' +
            formatDate(res[i].groupMsgTime) +
            '</span></p>' +
            '</div>';
        }
        $(".chatBox").append(l);
        messageList.push(l);
        localStorage.setItem("oldOtherGroupID", localStorage.getItem("otherGroupID"));
      }
    },
    error: function () {
      alert("获取聊天记录失败！")
    }
  })
}

$(document).on('click', '.block', function () {
  isFriend = true;
  $('.blockSelect').attr("class", "block");
  $(this).attr("class", "blockSelect");
  $('.rightSide .header').empty();
  console.log(this.dataset.id);
  localStorage.setItem("otherID", this.dataset.id);
  $.ajax({
    url: rootUrl + "/user/" + this.dataset.id,
    type: "GET",
    dataType: 'json',
    success: function (res) {
      let avatar = (res.gender === 1)?"./images/male.png":"./images/female.png";
      let i = '';
      i += '<div class="imgText">' +
        '<div class="userimg">' +
        '<img src="' + avatar +
        '" alt="" class="cover">' +
        '</div>' +
        '<h4 class="toName">' +
        res.name +
        '<br><span class="status">好友</span></h4>' +
        '</div>';
      $('.rightSide .header').append(i);
    },
    error: function () {
      alert("获取用户信息失败！")
    }
  });
  getMessages();
});

$(document).on('click', '.chehui', function () {
  var self = this;
  // console.log(this.dataset.id.length);
  if (!isFriend) {
    $.ajax({
      url: rootUrl + "/groupMessage",
      type: "DELETE",
      data: {
        groupMsgId: this.dataset.id
      },
      dataType: 'json',
      success: function (res) {
        // console.log(res);
        $(self).parent().parent().css("display", "none");
        alert("撤回信息成功！");
      },
      error: function () {
        alert("撤回信息失败！")
      }
    })
  } else {
    $.ajax({
      url: rootUrl + "/message",
      type: "DELETE",
      data: {
        msgId: this.dataset.id
      },
      dataType: 'json',
      success: function (res) {
        $(self).parent().parent().css("display", "none");
        alert("撤回信息成功！");
      },
      error: function () {
        alert("撤回信息失败！")
      }
    })
  }
})

$(document).on('click', '.deleteFriend', function () {
  var self = this;
  var friendID = this.dataset.id;
  // var friendID=$(this).parent().parent().parent().dataset.id;
  $.ajax({
    url: rootUrl + "/friend",
    type: "DELETE",
    data: {
      userId: id,
      friendId: friendID
    },
    dataType: 'json',
    success: function (res) {
      $(self).parent().parent().parent().css("display", "none");
      alert("删除好友成功！");
    },
    error: function () {
      alert("删除好友失败！")
    }
  })
})

$(document).on('click', '.blockGroup', function () {
  isFriend = false;
  $('.blockGroupSelect').attr("class", "blockGroup");
  $(this).attr("class", "blockGroupSelect");
  $('.rightSide .header').empty();
  console.log(this.dataset.id);
  localStorage.setItem("otherGroupID", this.dataset.id);
  $.ajax({
    url: rootUrl + "/group/" + this.dataset.id,
    type: "GET",
    dataType: 'json',
    success: function (res) {
      var i = '';
      i += '<div class="imgText">' +
        '<div class="userimg">' +
        '<img src="./images/heart.png" alt="" class="cover">' +
        '</div>' +
        '<h4 class="toName">' +
        res.groupName +
        '</h4>' +
        '</div>';
      $('.rightSide .header').append(i);
      localStorage.setItem("groupStarMessage", res.groupStarMessage);
    },
    error: function () {
      alert("获取群信息失败！")
    }
  })
  getGroupMessages();
})

setInterval(function () {
  if (isGerenzhongxin) {
    localStorage.setItem("oldOtherID", "");
    localStorage.setItem("oldOtherGroupID", "");
    return;
  }
  if (isFriend) {
    getMessages();
  } else {
    getGroupMessages();
  }
}, 1000)