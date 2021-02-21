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
  var types = {
    MENU: "菜单",
    FUNCTION: "功能",
    BLOCK: "区域",
  };
  var permissionGrid = $("#permissionGrid");
  permissionGrid.treegrid({
    fit: true,
    border: false,
    url: '/system/permission/list',
    idField: 'id',
    treeField: 'name',
    columns: [[
      {field: 'name', title: '名称', width: 180},
      {field: 'permissionKey', title: '标识', width: 150},
      {
        field: 'type', title: '类型', align: 'center', width: 80, formatter: function (val) {
        return types[val];
      }
      },
      {field: 'path', title: '路径', width: 200},
      {field: 'resource', title: '资源', width: 200},
      {field: 'weight', title: '权重', align: 'center', width: 60},
      {field: 'description', title: '描述', width: 200},
      {
        field: 'enable', title: '状态', width: 80, align: 'center', formatter: function (val) {
        return val ? "可用" : "禁用";
      }
      },
      {
        field: 'edit', title: '操作', width: 100, align: 'center', formatter: function (val, row) {
        var btns = [];
        btns.push('<a data-id="' + row.id + '" class="actions fa fa-pencil-square-o edit">编辑</a>');
        btns.push('<a data-id="' + row.id + '" class="actions fa fa-trash-o delete">删除</a>');
        return btns.join("");
      }
      }
    ]],
    toolbar: [{
      iconCls: 'fa fa-plus',
      text: '创建权限',
      handler: function () {
        formDialog();
      }
    }]
  });


  var gridPanel = permissionGrid.treegrid("getPanel");

  //给操作按钮绑定事件
  gridPanel.on("click", "a.edit", function () {
    var id = this.dataset.id;
    formDialog(id);
  }).on("click", "a.delete", function () {
    var id = this.dataset.id;
    $.messager.confirm("提示", "是否删除？", function (r) {
      if (r) {
        $.get("/system/permission/delete?id=" + id).success(function () {
          //删除成功
          permissionGrid.treegrid("reload");
        });
      }
    })
  });

  /**
   * 表单窗口
   */
  function formDialog(id) {
    var dialog = $("<div/>").dialog({
      iconCls: 'fa fa-plus',
      title: (id ? '编辑' : '创建') + '权限',
      href: '/system/permission/' + (id ? 'load?id=' + id : 'form'),
      width: 380,
      height: 450,
      onClose: function () {
        //销毁窗口
        $(this).dialog("destroy");
      },
      buttons: [
        {
          text: '保存',
          handler: function () {
            var permissionForm = $("#permissionForm");
            if (permissionForm.form("validate")) {
              $.post("/system/permission/" + (id ? 'update' : 'save'),
                permissionForm.serialize()
              ).success(function () {
                permissionGrid.treegrid("reload");
                dialog.dialog('close');
              });
            }
          }
        }
      ]
    });
  }
});