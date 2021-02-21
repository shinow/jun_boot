let Progress = function(init) {
    if (init.type == 'circle' || init.type == 1) {
        this.initCircle(init);
    } else if (init.type == 'bootstrap' || init.type == 2) {
        this.initBootstrap(init);
    }
};
Progress.prototype = {
    initCircle: function(init) {
        this.el = init.el;
        let cvsElement = document.getElementById(this.el),
            ctx = cvsElement.getContext("2d"),
            width = cvsElement.width,
            height = cvsElement.height,
            degActive = 0,
            timer = null;
        init.deg >= 0 && init.deg <= 100 ? this.deg = init.deg: this.deg = 100;
        init.lineWidth !== undefined ? this.lineWidth = init.lineWidth: this.lineWidth = 20;
        this.wh = width > height ? height: width;
        init.circleRadius > 0 && init.circleRadius <= this.wh / 2 - this.lineWidth / 2 ? this.circleRadius = init.circleRadius: this.circleRadius = this.wh / 2 - this.lineWidth / 2;
        init.lineBgColor !== undefined ? this.lineBgColor = init.lineBgColor: this.lineBgColor = '#ccc';
        init.lineColor !== undefined ? this.lineColor = init.lineColor: this.lineColor = '#009ee5';
        init.textColor !== undefined ? this.textColor = init.textColor: this.textColor = '#009ee5';
        init.fontSize !== undefined ? this.fontSize = init.fontSize: this.fontSize = parseInt(this.circleRadius / 2);
        this.timer = init.timer;
        if (window.devicePixelRatio) {
            cvsElement.style.width = width + "px";
            cvsElement.style.height = height + "px";
            cvsElement.height = height * window.devicePixelRatio;
            cvsElement.width = width * window.devicePixelRatio;
            ctx.scale(window.devicePixelRatio, window.devicePixelRatio);
        }
        ctx.lineWidth = this.lineWidth;
        timer = setInterval(function() {
            ctx.clearRect(0, 0, width, height);
            ctx.beginPath();
            ctx.arc(width / 2, height / 2, this.circleRadius, 1, 8);
            ctx.strokeStyle = this.lineBgColor;
            ctx.stroke();
            ctx.beginPath();
            ctx.arc(width / 2, height / 2, this.circleRadius, -Math.PI / 2, degActive * Math.PI / 180 - Math.PI / 2);
            ctx.strokeStyle = this.lineColor;
            ctx.stroke();
            let txt = (parseInt(degActive * 100 / 360) + "%");
            ctx.font = this.fontSize + "px SimHei";
            let w = ctx.measureText(txt).width;
            let h = this.fontSize / 2;
            ctx.fillStyle = this.textColor;
            ctx.fillText(txt, width / 2 - w / 2, height / 2 + h / 2);
            if (degActive >= this.deg / 100 * 360) {
                clearInterval(timer);
                timer = null;
            }
            degActive++;
        }.bind(this), this.timer);
    },
    initBootstrap: function(init) {
        let self     = this;
        self.el      = init.el;
        self.flag    = false;
        self.bgColor = init.bgColor !== undefined ? init.bgColor: 'progress-striped';
        let slide_element_id = document.getElementById(this.el.getAttribute("id"));
        let text_element     = init.textId;
        let clear_element    = init.clearId;
        let left     = 0;
        let orginX1  = 0;
        let width    = $(slide_element_id).width();
        let offsetL  = $(slide_element_id).offset().left;
        let val      = $(slide_element_id).children(".progress-star").val();
        //添加样式
        slide_element_id.classList.add(self.bgColor);
        //定义鼠标原始位置
        slide_element_id.onmousedown = function(e) {
            self.flag   = true;
            orginX1     = e.pageX - offsetL ; //开始的点
            left        = orginX1;
            val         = parseInt(( left / width ) * 100);
            $(slide_element_id).children(".progress-bar").css({"width": val + '%'});
            $(slide_element_id).children(".progress-star").val(val);
            $(slide_element_id).next().find("span").html(val + '%');


            //定义开始滑动
            document.body.onmousemove = function (e) {
                if ( self.flag ) {
                    left = e.pageX - offsetL - orginX1 + orginX1;
                    if ( left <= 0 ) {
                        left = 0;
                    } else if ( left > width ) {
                        left = width;
                    }
                    val = parseInt((left / width) * 100);
                    $(slide_element_id).children(".progress-bar").css({"width": val + '%'});
                    $(slide_element_id).children(".progress-star").val(val);
                    $(slide_element_id).next().find("span").html(val + '%');
                }
            }

            //定义结束
            document.body.onmouseup = function(e) {
                let _flag =  self.flag;
                self.flag = false;
                if( _flag )  readerCircle(slide_element_id,val);
            }
        }
        //清空事件
        clear_element.onclick = function(e) {
            left = 0;
            let val = parseInt((left / width) * 100);
            $(slide_element_id).children(".progress-bar").css({"width": val + '%'});
            $(slide_element_id).children(".progress-star").val(val);
            $(slide_element_id).next().find("span").html(val + '%');
            readerCircle(slide_element_id,val);
        }

        //重新渲染circle,根据规则组装字符串
        readerCircle=function(slide_element_id,val){
            let idDom     = $(slide_element_id).attr("id");
            let idDomArr  = idDom.split("-");
            if(idDomArr.length == 3){
                circleId = "kr-progress-canvas-"+idDomArr[2];
                new Progress({
                    type: "circle",
                    el: circleId, //canvas元素id
                    deg: parseFloat( val ),
                    timer: 2,
                    lineWidth: 5,
                    lineBgColor: '#fffff',
                    lineColor: '#409eff',
                    textColor: '#000000',
                    fontSize: 16,
                    circleRadius: 30,
                    width:100,
                    height:100,
                })
            }
        }
    }
};