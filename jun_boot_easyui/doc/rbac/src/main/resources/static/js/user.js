/**
 *<p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : static.js</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年11月13日</li>
 * <li>@author      : ____′↘夏悸 <wmails@126.com></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
$(function () {
  var userGrid = $("#userGrid");

  var userGridAction = $("#userGridAction");

  userGrid.datagrid({
    fit: true,
    border: false,
    url: '/system/user/list',
    singleSelect: true,
    pagination: true,
    rownumbers: true,
    columns: [[
      {field: 'account', title: '账号', width: 180},
      {field: 'userName', title: '姓名', width: 150},
      {field: 'tel', title: '电话', width: 200},
      {
        field: 'roles', title: '角色', width: 200, formatter: function (val) {
          var roles = [];
          $.each(val, function () {
            roles.push(this.roleName);
          });
          return roles.join(",");
        }
      },
      {
        field: 'enable', title: '状态', width: 80, align: 'center', formatter: function (val) {
          return val ? "可用" : "禁用";
        }
      },
      {
        field: 'edit', title: '操作', width: 100, align: 'center', formatter: function (val, row) {
          return userGridAction.children("a.actions").attr('data-id', row.id).end().html();
        }
      }
    ]],
    toolbar: "#userGridToolbar"
  });


  var gridPanel = userGrid.datagrid("getPanel");

  //给操作按钮绑定事件
  gridPanel.on("click", "a.edit", function () {
    var id = this.dataset.id;
    formDialog(id);
  }).on("click", "a.delete", function () {
    var id = this.dataset.id;
    $.messager.confirm("提示", "是否删除？", function (r) {
      if (r) {
        $.get("/system/user/delete?id=" + id).success(function () {
          //删除成功
          userGrid.datagrid("reload");
        });
      }
    })
  }).on("click", "a.create", function () {
    formDialog();
  });

  /**
   * 表单窗口
   */
  function formDialog(id) {
    var dialog = $("<div/>").dialog({
      iconCls: 'fa fa-plus',
      title: (id ? '编辑' : '创建') + '用户',
      href: '/system/user/' + (id ? 'load?id=' + id : 'form'),
      width: 380,
      height: 300,
      modal: true,
      onClose: function () {
        //销毁窗口
        $(this).dialog("destroy");
      },
      buttons: [
        {
          text: '保存',
          handler: function () {
            var userForm = $("#userForm");
            if (userForm.form("validate")) {
              $.post("/system/user/" + (id ? 'update' : 'save'),
                userForm.serialize()
              ).success(function () {
                userGrid.datagrid("reload");
                dialog.dialog('close');
              });
            }
          }
        }
      ]
    });
  }
});