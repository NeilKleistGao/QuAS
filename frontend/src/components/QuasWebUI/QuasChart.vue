<template>
    <div>
        <div :id="name" :style="chartStyle"></div>
    </div>
</template>

<script>
    export default {
        name: "QuasChart",
        props: {
            /**
             * 图表标题
             */
            title: {
                type: String,
                default: "chart"
            },
            /**
             * 图表宽度，单位为像素
             */
            width: {
                type: Number,
                default: 400
            },
            /**
             * 图表高度，单位为像素
             */
            height: {
                type: Number,
                default: 300
            },
            /**
             * 图表的x轴信息
             */
            xAxis: {
                type: Object
            },
            /**
             * 图表的y轴信息
             */
            yAxis: {
                type: Object,
                default: () => {return {};}
            },
            /**
             * 图表的相关行为
             */
            legend: {
                type: Object,
                default: null
            },
            /**
             * 图表的数据
             */
            series: {
                type: Array,
                required: true
            },
            /**
             * 图表div对象的id
             */
            name: {
                type: String,
                required: true
            },
            /**
             * 图表视觉映射组件
             */
            visualMap: {
                type: Object
            },
            /**
             * 图表主题
             */
            theme: {
                type: String,
                default: "light"
            },
            /**
             * 图表是否需要加载地图数据
             */
            loadMap: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                done: false
            }
        },
        mounted() {
            if (!this.loadMap) {
                this.init();
            }
            else {
                this.$axios.get("/china.json").then((res) => {
                    // console.log(res.data);
                    this.$echarts.registerMap("china", res.data);
                    this.init();
                    this.done = true;
                });
            }
        },
        methods: {
            /**
             * 初始化图表
             * @private
             */
            init() {
                let chart = this.$echarts.init(document.getElementById(this.name), this.theme);
                let option = null;

                if (this.xAxis == undefined) {
                    option = {
                        title: {
                            text: this.title
                        },
                        tooltip: {},
                        legend: this.legend,
                        series: this.series,
                        visualMap: this.visualMap
                    };
                }
                else {
                    option = {
                        title: {
                            text: this.title
                        },
                        tooltip: {},
                        legend: this.legend,
                        xAxis: this.xAxis,
                        yAxis: this.yAxis,
                        series: this.series,
                        visualMap: this.visualMap
                    };
                }

                chart.setOption(option);
            }
        },
        computed: {
            chartStyle() {
                return "width:" + this.width + "px; height:" + this.height + "px;";
            }
        }
    }
</script>

<style scoped>
    div {
        margin-left: 20px;
        margin-top: 20px;
    }
</style>