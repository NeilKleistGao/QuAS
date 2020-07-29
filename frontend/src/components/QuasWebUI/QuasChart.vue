<template>
    <div>
        <div :id="name" :style="chartStyle"></div>
    </div>
</template>

<script>
    export default {
        name: "QuasChart",
        props: {
            title: {
                type: String,
                default: "chart"
            },
            width: {
                type: Number,
                default: 400
            },
            height: {
                type: Number,
                default: 300
            },
            xAxis: {
                type: Object
            },
            yAxis: {
                type: Object,
                default: () => {return {};}
            },
            legend: {
                type: Object,
                default: null
            },
            series: {
                type: Array,
                required: true
            },
            name: {
                type: String,
                required: true
            },
            visualMap: {
                type: Object
            },
            theme: {
                type: String,
                default: "light"
            },
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