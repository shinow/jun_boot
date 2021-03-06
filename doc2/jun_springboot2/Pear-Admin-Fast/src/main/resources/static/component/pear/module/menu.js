layui.define(['table', 'jquery', 'element'], function(exports) {
	"use strict";

	var MOD_NAME = 'menu',
		$ = layui.jquery,
		element = layui.element;

	var pearMenu = function(opt) {
		this.option = opt;
	};

	pearMenu.prototype.render = function(opt) {
		var option = {
			elem: opt.elem,
			async: opt.async ? opt.async : false,
			parseData: opt.parseData,
			url: opt.url,
			defaultOpen: opt.defaultOpen,
			defaultSelect: opt.defaultSelect,
			control: opt.control,
			defaultMenu: opt.defaultMenu,
			accordion: opt.accordion,
			height: opt.height,
			theme: opt.theme,
			data: opt.data ? opt.data : [],
			change: opt.change ? opt.change : function() {
			},
			done: opt.done ? opt.done : function() {}
		}

		if (option.async) {
			getData(option.url).then(function(data){
				option.data = data;
				if (option.parseData != false) {
					option.parseData(option.data);
				}
				if (option.data.length > 0) {
					if (option.control != false) {
						createMenuAndControl(option);
					} else {
						createMenu(option);
					}
				}
				element.init();
				downShow(option);
				option.done();
			});
		}
		return new pearMenu(opt);
	}

	pearMenu.prototype.click = function(clickEvent) {
		var _this = this;
		$("body").on("click","#" + _this.option.elem + " .site-demo-active",function(){
			var dom = $(this);
			var data = {
				menuId: dom.attr("menu-id"),
				menuTitle: dom.attr("menu-title"),
				menuPath: dom.attr("menu-title"),
				menuIcon: dom.attr("menu-icon"),
				menuUrl: dom.attr("menu-url")
			};
			var doms = hash(dom);
			if (doms.text() != '') {
				data['menuPath'] = doms.find("span").text() + " / " + data['menuPath'];
			}
			var domss = hash(doms);
			if (domss.text() != '') {
				data['menuPath'] = domss.find("span").text() + " / " + data['menuPath'];
			}
			var domsss = hash(domss);
			if (domsss.text() != '') {
				data['menuPath'] = domsss.find("span").text() + " / " + data['menuPath'];
			}
			clickEvent(dom, data);
		})
	}

	function hash(dom) {
		return dom.parent().parent().prev();
	}

	pearMenu.prototype.skin = function(skin) {
		var menu = $(".pear-nav-tree[lay-filter='" + this.option.elem + "']").parent();
		menu.removeClass("dark-theme");
		menu.removeClass("light-theme");
		menu.addClass(skin);
	}

	pearMenu.prototype.selectItem = function(pearId) {
		if (this.option.control != false) {
			$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents(".layui-side-scroll ").find("ul").css({
				display: "none"
			});
			$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents(".layui-side-scroll ").find(".layui-this").removeClass(
				"layui-this");
			$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents("ul").css({
				display: "block"
			});
			var controlId = $("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents("ul").attr("pear-id");

			$("#" + this.option.control).find(".layui-this").removeClass("layui-this");
			$("#" + this.option.control).find("[pear-id='" + controlId + "']").addClass("layui-this");
		}
		if (this.option.accordion == true) {
			$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents(".pear-nav-tree").find(".layui-nav-itemed").removeClass(
				"layui-nav-itemed");
		}
		$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents(".pear-nav-tree").find(".layui-this").removeClass(
			"layui-this");
		if (!$("#" + this.option.elem).is(".pear-nav-mini")) {
			$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents(".layui-nav-item").addClass("layui-nav-itemed");
			$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parents("dd").addClass("layui-nav-itemed");
		}

		$("#" + this.option.elem + " a[menu-id='" + pearId + "']").parent().addClass("layui-this");
	}

	var activeMenus;
	pearMenu.prototype.collaspe = function(time) {
		var elem = this.option.elem;
		var config = this.option;
		if ($("#" + this.option.elem).is(".pear-nav-mini")) {
			$.each(activeMenus, function(i, item) {
				$("#" + elem + " a[menu-id='" + $(this).attr("menu-id") + "']").parent().addClass("layui-nav-itemed");
			})
			$("#" + this.option.elem).removeClass("pear-nav-mini");
			$("#" + this.option.elem).animate({
				width: "220px"
			}, 150);
			isHoverMenu(false, config);
		} else {
			activeMenus = $("#" + this.option.elem).find(".layui-nav-itemed>a");
			$("#" + this.option.elem).find(".layui-nav-itemed").removeClass("layui-nav-itemed");
			$("#" + this.option.elem).addClass("pear-nav-mini");
			$("#" + this.option.elem).animate({
				width: "60px"
			}, 400);
			isHoverMenu(true, config);

		}
	}

	function getData(url){
		var defer = $.Deferred();
		$.get(url+"?fresh=" + Math.random(), function(result) {
			defer.resolve(result)
		});
		return defer.promise();
	}

	function createMenu(option) {
		var menuHtml = '<ul lay-filter="' + option.elem +
			'" class="layui-nav arrow   pear-menu layui-nav-tree pear-nav-tree">'
		$.each(option.data, function(i, item) {
			var content = '<li class="layui-nav-item" >';
			if (i == option.defaultOpen) {
				content = '<li class="layui-nav-item layui-nav-itemed" >';
			}
			var href = "javascript:;";
			var target = "";
			var className = "site-demo-active"
			if (item.openType == "_blank" && item.type == 1) {
				href = item.href;
				target = "target='_blank'";
				className = "";
			}
			if (item.type == 0) {
				// ??? ??? ??? ??? ??? ???
				content += '<a  href="javascript:;" menu-type="' + item.type + '" menu-id="' + item.id + '" href="' + href +
					'" ' + target + '><i class="' + item.icon + '"></i><span>' + item.title +
					'</span></a>';
			} else if (item.type == 1) {
				content += '<a class="' + className + '" menu-type="' + item.type + '" menu-url="' + item.href + '" menu-id="' +
					item.id +
					'" menu-title="' + item.title + '"  href="' + href + '"  ' + target + '><i class="' + item.icon +
					'"></i><span>' + item.title + '</span></a>';
			}
			// ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? 
			content += loadchild(item);
			// ??? ??? ??? ??? ??? ??? ??? ???
			content += '</li>';
			menuHtml += content;
		});
		// ??? ??? ??? ??? ??? ??? ??? ??? ??? ???
		menuHtml += "</ul>";
		// ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ???
		$("#" + option.elem).html(menuHtml);
	}

	function createMenuAndControl(option) {
		var control = '<ul class="layui-nav  pear-nav-control pc layui-hide-xs">';
		var controlPe = '<ul class="layui-nav pear-nav-control layui-hide-sm">';
		// ??? ??? ??? ???
		var menu = '<div class="layui-side-scroll ' + option.theme + '">'
		// ??? ??? ??? ??? ??? ???
		var index = 0;
		var controlItemPe = '<dl class="layui-nav-child">';
		$.each(option.data, function(i, item) {
			var menuItem = '';
			var controlItem = '';
			if (index === option.defaultMenu) {
				controlItem = '<li pear-href="' + item.href + '" pear-title="' + item.title + '" pear-id="' + item.id +
					'" class="layui-this layui-nav-item"><a href="#">' + item.title + '</a></li>';
				menuItem = '<ul  pear-id="' + item.id + '" lay-filter="' + option.elem +
					'" class="layui-nav arrow layui-nav-tree pear-nav-tree">';
				// ???????????????
				controlPe += '<li class="layui-nav-item"><a class="pe-title" href="javascript:;" >'+ item.title +'</a>';
				controlItemPe += '<dd  pear-href="' + item.href + '" pear-title="' + item.title + '" pear-id="' + item.id +'"><a href="javascript:void(0);">'+ item.title +'</a></dd>';
			} else {
				controlItem = '<li  pear-href="' + item.href + '" pear-title="' + item.title + '" pear-id="' + item.id +
					'" class="layui-nav-item"><a href="#">' + item.title + '</a></li>';
				menuItem = '<ul style="display:none" pear-id="' + item.id + '" lay-filter="' + option.elem +
					'" class="layui-nav arrow layui-nav-tree pear-nav-tree">';

				controlItemPe += '<dd pear-href="' + item.href + '" pear-title="' + item.title + '" pear-id="' + item.id +'"><a href="javascript:void(0);">'+ item.title +'</a></dd>';

			}
			index++;
			$.each(item.children, function(i, note) {
				// ??? ??? ??? ??? ??? ??? ??? ???
				var content = '<li class="layui-nav-item" >';
				var href = "javascript:;";
				var target = "";
				var className = "site-demo-active";
				if (note.openType == "_blank" && note.type == 1) {
					href = note.href;
					target = "target='_blank'";
					className = "";
				}
				// ??? ??? ??? ??? ??? ??? 0 ??? ????????????????????? 1 ??? ??? ??? ??? ??? ??? ??? ??? ???
				if (note.type == 0) {
					// ??? ??? ??? ??? ??? ???
					content += '<a  href="' + href + '" ' + target + ' menu-type="' + note.type + '" menu-id="' + note.id +
						'" ><i class="' + note.icon + '"></i><span>' + note.title +
						'</span></a>';
				} else if (note.type == 1) {
					// ??? ??? ??? ??? ??? ???
					content += '<a ' + target + ' class="' + className + '" menu-type="' + note.type + '" menu-url="' + note.href +
						'" menu-id="' + note.id +
						'" menu-title="' + note.title + '" href="' + href + '"><i class="' + note.icon +
						'"></i><span>' + note.title + '</span></a>';
				}
				content += loadchild(note);
				content += '</li>';
				menuItem += content;
			})
			menu += menuItem + '</ul>';
			control += controlItem;

		})
		controlItemPe += "</li></dl></ul>"
		controlPe += controlItemPe;
		$("#" + option.control).html(control);
		$("#" + option.control).append(controlPe);
		$("#" + option.elem).html(menu);
		$("#" + option.control + " .pear-nav-control").on("click", "[pear-id]", function() {
			$("#" + option.elem).find(".pear-nav-tree").css({
				display: 'none'
			});
			$("#" + option.elem).find(".pear-nav-tree[pear-id='" + $(this).attr("pear-id") + "']").css({
				display: 'block'
			});
			$("#" + option.control).find(".pe-title").html($(this).attr("pear-title"));
			$("#" + option.control).find("")
			option.change($(this).attr("pear-id"), $(this).attr("pear-title"), $(this).attr("pear-href"))
		})
	}

	/** ??????????????? (??????)*/
	function loadchild(obj) {
		// ??? ??? ??? ??? ??? ??? ?????? ??? ??? ??? ??? ??? ??? ??? ??? ???
		if (obj.type == 1) {
			return "";
		}
		// ??? ??? ??? ??? ??? ??? ???
		var content = '<dl class="layui-nav-child">';
		// ??? ??? ??? ??? ??? ??? ??? ??? 
		if (obj.children != null && obj.children.length > 0) {
			// ??? ??? ??? ??? ???
			$.each(obj.children, function(i, note) {
				// ??? ??? ??? ??? ??? ???
				content += '<dd>';
				var href = "javascript:;";
				var target = "";
				var className = "site-demo-active";
				if (note.openType == "_blank" && note.type == 1) {
					href = note.href;
					target = "target='_blank'";
					className = "";
				}
				// ??? ??? ??? ??? ??? ???
				if (note.type == 0) {
					// ??? ??? ??? ??? ??? ???
					content += '<a ' + target + '  href="' + href + '" menu-type="' + note.type + '" menu-id="' + note.id +
						'"><i class="' + note.icon + '"></i><span>' + note.title + '</span></a>';
				} else if (note.type == 1) {
					// ??? ??? ??? ??? ??? ???
					content += '<a ' + target + ' class="' + className + '" menu-type="' + note.type + '" menu-url="' + note.href +
						'" menu-id="' + note.id + '" menu-title="' + note.title + '" menu-icon="' + note.icon + '" href="' + href +
						'" ><i class="' + note.icon + '"></i><span>' + note.title + '</span></a>';
				}
				// ??? ??? ??? ??? ??? ???
				content += loadchild(note);
				// ??? ??? ??? ??? ??? ??? ???
				content += '</dd>';
			});
			// ??? ???
		} else {
			content += '<div class="toast"> ??? ??? ??? </div>';
		}
		content += '</dl>';
		return content;
	}

	function downShow(option) {
		$("body #" + option.elem).on("click", "a[menu-type='0']", function() {
			if (!$("#" + option.elem).is(".pear-nav-mini")) {
				var superEle = $(this).parent();
				var ele = $(this).next('.layui-nav-child');
				var heights = ele.children("dd").length * 48;

				if ($(this).parent().is(".layui-nav-itemed")) {
					if (option.accordion) {
						$(this).parent().parent().find(".layui-nav-itemed").removeClass("layui-nav-itemed");
						$(this).parent().addClass("layui-nav-itemed");
					}
					ele.height(0);
					ele.animate({
						height: heights + "px"
					}, 200, function() {
						ele.css({
							height: "auto"
						});
					});
				} else {
					$(this).parent().addClass("layui-nav-itemed");
					ele.animate({
						height: "0px"
					}, 200, function() {
						ele.css({
							height: "auto"
						});
						$(this).parent().removeClass("layui-nav-itemed");
					});
				}
			}
		})
	}

	/** ??? ??? ??? ??? ??? ???*/
	function isHoverMenu(b, option) {
		if (b) {
			$("#" + option.elem + ".pear-nav-mini .layui-nav-item,#" + option.elem + ".pear-nav-mini dd").hover(function() {
				$(this).children(".layui-nav-child").addClass("layui-nav-hover");

				var top = $(this).offset().top + 5;
				var y = window.document.body.clientHeight;

				var height = $(window).height();

				var topLength = $(this).offset().top;

				var thisHeight = $(this).children(".layui-nav-child").height();

				if((thisHeight+topLength)>height){
					topLength = height-thisHeight-10;
				}
				if (!$(this).is(".layui-nav-item")) {
					var left = $(this).offset().left + $(this).width()+2;
					$(this).children(".layui-nav-child").offset({
						left: left
					});
				} else {
					var left = $(this).offset().left + 62;
					$(this).children(".layui-nav-child").offset({
						left: left
					});
				}
				$(this).children(".layui-nav-child").offset({
					top: topLength
				});
			}, function() {
				$(this).children(".layui-nav-child").removeClass("layui-nav-hover");
				$(this).children(".layui-nav-child").css({
					left: '0px'
				});
				$(this).children(".layui-nav-child").css({
					top: '0px'
				});
			})
		} else {
			$("#" + option.elem + " .layui-nav-item").off('mouseenter').unbind('mouseleave');
			$("#" + option.elem + " dd").off('mouseenter').unbind('mouseleave');
		}
	}
	exports(MOD_NAME, new pearMenu());
})
