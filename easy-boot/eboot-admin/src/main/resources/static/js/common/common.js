var Common = function () {
    var initTable = function (ele, url, cols, table, doneCallBack) {
        return table.render({
            elem: ele
            , url: url
            , method: 'POST'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: cols
            , page: {
                limits: [10, 20, 50, 100]
            },
            request: {
                pageName: 'current',
                limitName: 'size'
            },
            done: function (res, curr, count) {
                if (typeof(doneCallBack) === "function") {
                    doneCallBack(res);
                }
            }
        });
    };

    var initSimpleTable = function (ele, url, cols, table, doneCallBack) {
        return table.render({
            elem: ele
            , url: url
            , method: 'POST'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: cols
            , done: function (res, curr, count) {
                if (typeof(doneCallBack) === "function") {
                    doneCallBack(res);
                }
            }
        });
    };

    var searchTable = function (formId, tableIns, isPage) {
        var queryParams = getParams(formId);
        var page;
        if (isPage === false) {
            page = isPage
        } else {
            page = {curr: 1}
        }
        // console.log(page);
        tableIns.reload({
            where: {condition: queryParams},
            page: page
        });
    };

    var getParams = function (formId) {
        var $ = layui.jquery;
        var _params = {};
        $.each($('#' + formId).serializeArray(), function (i, field) {
            if (null != field.value && "" != field.value) {
                _params[field.name] = field.value;
            }
        });
        return _params;
    };

    var upload = function (eleId, layUpload, done, extendParam, error, accept, exts) {
        layUpload.render({
            elem: eleId //绑定元素
            , url: uploadPath //上传接口
            , accept: accept === undefined ? 'file' : accept
            , exts: exts === undefined ? 'jpg|png|gif|bmp|jpeg' : exts
            , data: extendParam
            , done: function (res) {
                //上传完毕回调
                if (typeof (done) === 'function') {
                    if (res.code === '0') {
                        done(res.data);
                    }
                }
            }
            , error: function () {
                //请求异常回调
                if (typeof (error) === 'function') {
                    error()
                }
            }
        });
    };

    var openFrame = function (url, title, width, height, endFunction) {
        width = width === undefined ? '900px' : width;
        height = height === undefined ? '500px' : height;
        return layer.open({
            area: [width, height],
            type: 2,
            title: title,
            content: url, //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            end: function () {
                if (typeof (endFunction) === 'function') {
                    endFunction();
                }
            }
        });
    };

    var ajaxFormSubmit = function (ajaxUrl, data, successFn, beforeFn,method) {
        console.log(method);
        layui.use('form', function () {
            var $ = layui.jquery, layer = layui.layer;
            $.ajax({
                type: method === undefined ? "POST" : method,
                cache: false,
                url: ajaxUrl,
                data: data,
                success: function (responseJson) {
                    if (typeof (successFn) === 'function' && responseJson.code === '0') {
                        successFn(responseJson);
                    } else {
                        layer.msg(responseJson.msg, {icon: 5});
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    if (typeof (beforeFn) === 'function') {
                        beforeFn();
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {

                }
            });
        })

    };
    
    var initChart = function (selectorId,type) {
        var myChart = echarts.init(document.getElementById(selectorId));
        // 显示标题，图例和空的坐标轴
        myChart.setOption({
            title: {
                text: '',
                left:'center'
            },
            tooltip: {},
            legend: {
                data:[],
                top: 'middle',
                right:'3%'
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '',
                type: type === undefined || type === null ? 'bar' : type,
                data: [],
                radius:[0,'50%']
            }]
        });
        return myChart;
    }
    var closeFrame = function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    };

    var boolFormat = function (value) {
        if (value === '1'){
            return '是';
        }else if(value === '0'){
            return '否';
        }else{
            return '-';
        }
    }
    return {
        initTable: function (ele, url, cols, table, doneCallBack) {
            return initTable(ele, url, cols, table, doneCallBack);
        },
        initSimpleTable: function (ele, url, cols, table, doneCallBack) {
            return initSimpleTable(ele, url, cols, table, doneCallBack);
        },
        searchTable: function (formId, table, isPage) {
            searchTable(formId, table, isPage);
        },
        uploadFile: function (eleId, layUpload, done, extendParam, error, accept, exts) {
            upload(eleId, layUpload, done, extendParam, error, accept, exts);
        },
        openFrame: function (url, title, width, height, endFunction) {
            return openFrame(url, title, width, height, endFunction);
        },
        ajaxFormSubmit: function (ajaxUrl, data, successFn, beforeFn, method) {
            return ajaxFormSubmit(ajaxUrl, data, successFn, beforeFn, method);
        },
        closeFrame: function () {
            closeFrame();
        },
        initChart: function (selectorId, type) {
            return initChart(selectorId, type);
        },
        boolFormat: function (value) {
            return boolFormat(value);
        }
    }
}();