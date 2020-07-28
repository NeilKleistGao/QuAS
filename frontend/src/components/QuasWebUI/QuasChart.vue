<template>
    <div :id="name" :style="chartStyle"></div>
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
            labels: {
                type: Array,
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
            }
        },
        mounted() {
            let chart = this.$echarts.init(document.getElementById(this.name));
            let option = null;

            if (this.labels == undefined) {
                option = {
                    title: {
                        text: this.title
                    },
                    tooltip: {},
                    legend: this.legend,
                    series: this.series
                };
            }
            else {
                option = {
                    title: {
                        text: this.title
                    },
                    tooltip: {},
                    legend: this.legend,
                    xAxis: {
                        data: this.labels
                    },
                    yAxis: {},
                    series: this.series
                };
            }

            chart.setOption(option);
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