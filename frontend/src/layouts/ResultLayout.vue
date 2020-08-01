<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links"/>

        <h1 class="quas-fill-header">{{title}}——填写结果</h1>
        <br/>

        <quas-tab :labels="tab_labels">
            <template slot="填写数据">
                <div class="quas-tab-slot">
                    <table class="quas-table">
                        <thead>
                            <tr>
                                <th v-for="(item, index) in questionnaire" :key="'th' + index.toString()">{{getQuestionnaireTitle(item.type, item.data.information)}}</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(result, index) in results" :key="'tr' + index.toString()">
                                <td v-for="(item, index2) in result" :key="'td' + index2.toString()">
                                    {{getResult(item)}}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div style="text-align: right; margin-top: 10px; margin-right: 10px">
                    <button class="quas-submit-button">导出excel</button>
                </div>

            </template>

            <template slot="统计图表">
                <div class="quas-tab-slot">
                    <div v-for="(item, index) in questionnaire" :key="'chart' + index.toString()" style="margin-bottom: 25px">
                        <span>第{{index + 1}}题：{{item.data.information}}</span>
                        <br/>

                        <div v-if="item.type === 'radio'">
                            <quas-chart title=""
                                        :legend="legend"
                                        :name="'chart' + index.toString()"
                                        :series="getChartSeries(index)"/>
                        </div>
                        <div v-else-if="item.type === 'check'">
                            <quas-chart title=""
                                        :x-axis="{data: item.data.content.labels}"
                                        :legend="legend"
                                        :name="'chart' + index.toString()"
                                        :series="getChartSeries(index)"/>
                        </div>
                        <div v-else-if="item.type === 'sort'">
                            <quas-chart title=""
                                        :x-axis="getHeatXAxis(index)"
                                        :y-axis="getHeatYAxis(index)"
                                        :name="'chart' + index.toString()"
                                        :series="getChartSeries(index)"
                                        :visual-map="visualMap"/>
                        </div>
                        <span v-else>该类题目暂时不支持绘制统计图表</span>
                        <hr/>
                    </div>
                </div>
            </template>

            <template slot="来源信息">
                <div class="quas-tab-slot">
                    <div class="quas-statistic-grid">
                        <quas-chart title="地区分布"
                                    :load-map="true"
                                    name="map"
                                    :series="mapSeries"
                                    :width="800"
                                    :height="600"
                                    :visual-map="map_visualMap"/>
                        <quas-chart title="近7日的时间分布"
                                    name="time"
                                    :x-axis="line_axis"
                                    :series="line_series"
                                    :width="800"
                                    :height="600"/>
                        <quas-chart title="专业分布"
                                    name="major"
                                    :width="800"
                                    :height="600"
                                    :legend="test_major_legend"
                                    :series="test_major_series"/>
                        <quas-chart title="年级分布"
                                    name="grade"
                                    :width="800"
                                    :height="600"
                                    :legend="test_grade_legend"
                                    :series="test_grade_series"/>
                    </div>
                </div>
            </template>
        </quas-tab>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasTab from "@/components/QuasWebUI/QuasTab";
    import QuasChart from "@/components/QuasWebUI/QuasChart";

    export default {
        name: "ResultLayout",
        components: {QuasChart, QuasTab, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试", "问卷分析测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154", "#/questionnaire/analysis/3154"],
                title: "关于问卷测试的问卷调查",
                tab_labels: ["填写数据", "统计图表", "来源信息"],
                questionnaire: [
                    {
                        type: "none",
                        data: {
                            information: "这是一个测试",
                            content: null,
                            next: 0
                        }
                    },
                    {
                        type: "radio",
                        data: {
                            information: "这是一个单选题",
                            content: {
                                labels: ["选项1", "选项2", "选项3"],
                                next: [3, 4, 5]
                            },
                            next: 0
                        }
                    },
                    {
                        type: "check",
                        data: {
                            information: "这是一个多选题",
                            content: {
                                labels: ["选项1", "选项2", "选项3"]
                            },
                            next: -1
                        }
                    },
                    {
                        type: "date",
                        data: {
                            information: "这是一个日期",
                            content: {
                                type: "date"
                            },
                            next: -1
                        }
                    },
                    {
                        type: "text",
                        data: {
                            information: "这是一个填空题",
                            content: {
                            },
                            next: 0
                        }
                    },
                    {
                        type: "rich",
                        data: {
                            information: "这是一个多行文本",
                            content: {

                            },
                            next: 0
                        }
                    },
                    {
                        type: "drop",
                        data: {
                            information: "这是一个下拉选项",
                            content: {
                                labels: ["题目1"],
                                items: [{
                                    level: 0,
                                    label: "选项1"
                                },{
                                    level: 0,
                                    label: "选项2"
                                }]
                            },
                            next: 0
                        }
                    },
                    {
                        type: "sort",
                        data: {
                            information: "这是一个排序题",
                            content: {
                                labels: ["选项1", "选项2", "选项3"]
                            },
                            next: 0
                        }
                    }
                ],
                type_map: {
                    "none": "文字说明",
                    "radio": "单选题",
                    "check": "多选题",
                    "date": "日期",
                    "text": "填空题",
                    "rich": "多行文本",
                    "drop": "下拉选框",
                    "sort": "排序题"
                },
                results: [
                    [{},{result: "选项1"},{result: ["选项1"]},{result: "2020-09-23"},{result: "foo"},{result: "bar"},{result: ["选项1"]},{result: ["选项3", "选项1", "选项2"]}],
                    [{},{result: "选项2"},{result: ["选项1", "选项2"]},{result: "2020-09-23"},{result: "foo"},{result: "bar"},{result: ["选项1"]},{result: ["选项1", "选项2", "选项3"]}],
                    [{},{result: "选项3"},{result: ["选项1", "选项2", "选项3"]},{result: "2020-09-23"},{result: "foo"},{result: "bar"},{result: ["选项1"]},{result: ["选项2", "选项1", "选项3"]}]
                ],
                legend: {
                    data: ["人数"]
                },
                visualMap: {
                    min: 0,
                    max: 100,
                    calculable: true,
                    orient: "horizontal",
                    left: "center",
                    bottom: "0%",
                    inRange: {
                        color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
                    }
                },
                mapSeries: [{
                    type: "map",
                    mapType: "china",
                    name: "填写人数",
                    label: {
                        show: true,
                    },
                    data:[
                        {name: '北京',value: Math.round(Math.random()*1000)},
                        {name: '天津',value: Math.round(Math.random()*1000)},
                        {name: '上海',value: Math.round(Math.random()*1000)},
                        {name: '重庆',value: Math.round(Math.random()*1000)},
                        {name: '河北',value: Math.round(Math.random()*1000)},
                        {name: '河南',value: Math.round(Math.random()*1000)},
                        {name: '云南',value: Math.round(Math.random()*1000)},
                        {name: '辽宁',value: Math.round(Math.random()*1000)},
                        {name: '黑龙江',value: Math.round(Math.random()*1000)},
                        {name: '湖南',value: Math.round(Math.random()*1000)},
                        {name: '安徽',value: Math.round(Math.random()*1000)},
                        {name: '山东',value: Math.round(Math.random()*1000)},
                        {name: '新疆',value: Math.round(Math.random()*1000)},
                        {name: '江苏',value: Math.round(Math.random()*1000)},
                        {name: '浙江',value: Math.round(Math.random()*1000)},
                        {name: '江西',value: Math.round(Math.random()*1000)},
                        {name: '湖北',value: Math.round(Math.random()*1000)},
                        {name: '广西',value: Math.round(Math.random()*1000)},
                        {name: '甘肃',value: Math.round(Math.random()*1000)},
                        {name: '山西',value: Math.round(Math.random()*1000)},
                        {name: '内蒙古',value: Math.round(Math.random()*1000)},
                        {name: '陕西',value: Math.round(Math.random()*1000)},
                        {name: '吉林',value: Math.round(Math.random()*1000)},
                        {name: '福建',value: Math.round(Math.random()*1000)},
                        {name: '贵州',value: Math.round(Math.random()*1000)},
                        {name: '广东',value: Math.round(Math.random()*1000)},
                        {name: '青海',value: Math.round(Math.random()*1000)},
                        {name: '西藏',value: Math.round(Math.random()*1000)},
                        {name: '四川',value: Math.round(Math.random()*1000)},
                        {name: '宁夏',value: Math.round(Math.random()*1000)},
                        {name: '海南',value: Math.round(Math.random()*1000)},
                        {name: '台湾',value: Math.round(Math.random()*1000)},
                        {name: '香港',value: Math.round(Math.random()*1000)},
                        {name: '澳门',value: Math.round(Math.random()*1000)}
                    ]
                }],
                line_axis: {
                    type: "category",
                    data: ["7月26日", "7月27日", "7月28日", "7月29日", "7月30日", "7月31日", "8月1日"]
                },
                line_series: [{
                    data: [820, 932, 901, 934, 1290, 1330, 1320],
                    type: "line",
                    areaStyle: {}
                }],
                test_major_legend: {
                    left: "center",
                    top: "bottom",
                    data: ["计算机", "电子工程", "经济管理", "生物工程", "理论物理"]
                },
                test_grade_legend: {
                    left: "center",
                    top: "bottom",
                    data: ["本科一年级", "本科二年级", "本科三年级", "本科四年级"]
                },
                test_major_series: [
                    {
                        name: "人数",
                        type: "pie",
                        radius: [30, 110],
                        roseType: "area",
                        data: [
                            {name: "计算机", value: 10},
                            {name: "电子工程", value: 5},
                            {name: "经济管理", value: 1},
                            {name: "生物工程", value: 6},
                            {name: "理论物理", value: 4}
                        ]
                    }
                ],
                test_grade_series: [
                    {
                        name: "人数",
                        type: "pie",
                        radius: [30, 110],
                        roseType: "area",
                        data: [
                            {name: "本科一年级", value: 2},
                            {name: "本科二年级", value: 7},
                            {name: "本科三年级", value: 9},
                            {name: "本科四年级", value: 4},
                        ]
                    }
                ],
                map_visualMap: {
                    min: 0,
                    max: 1000,
                    inRange: {
                        color: ["lightskyblue", "yellow", "orangered"]
                    }
                }
            };
        },
        methods: {
            getQuestionnaireTitle(type, info) {
                return "(" + this.type_map[type] + ")" + info;
            },
            getResult(item) {
                if (typeof(item.result) === "string") {
                    return item.result;
                }
                else if (typeof(item.result) != "undefined") {
                    return item.result.join(", ");
                }
                else {
                    return "--";
                }
            },
            getRadioSeries(index) {
                let series = {
                    name: "人数",
                    type: "pie",
                    data: []
                };

                let mapping = {};
                let i = 0;
                for (let name of this.questionnaire[index].data.content.labels) {
                    series.data.push({
                        name: name,
                        value: 0
                    });

                    mapping[name] = i;
                    i++;
                }

                for (let r of this.results) {
                    series.data[mapping[r[index].result]].value++;
                }

                return series;
            },
            getCheckSeries(index) {
                let series = {
                    name: "人数",
                    type: "bar",
                    data: [],
                };

                let mapping = {};
                let i = 0;
                for (let name of this.questionnaire[index].data.content.labels) {
                    series.data.push({
                        name: name,
                        value: 0
                    });

                    mapping[name] = i;
                    i++;
                }

                for (let r of this.results) {
                    for (let i of r[index].result) {
                        series.data[mapping[i]].value++;
                    }
                }

                return series;
            },
            getSortSeries(index){
                let series = {
                    name: "频率%",
                    type: "heatmap",
                    data: []
                };

                let xs = this.getHeatXAxis(index).data;
                let ys = this.getHeatYAxis(index).data;
                let map = [];

                for (let i in xs) {
                    map.push([]);
                    // eslint-disable-next-line no-unused-vars
                    for (let j in ys) {
                        map[i].push(0);
                    }
                }

                for (let r of this.results) {
                    for (let i in r[index].result) {
                        let j = ys.indexOf(r[index].result[i]);
                        map[i][j]++
                    }
                }

                let sum = this.results.length;
                for (let i = 0; i < map.length; i++) {
                    for (let j = 0; j < map[i].length; j++) {
                        series.data.push([i, j, map[i][j] / sum * 100.0]);
                    }
                }

                return series;
            },
            getChartSeries(index) {
                let res = [];
                if (this.questionnaire[index].type === "radio") {
                    let series = this.getRadioSeries(index);
                    res.push(series);
                }
                else if (this.questionnaire[index].type === "check") {
                    let series = this.getCheckSeries(index);
                    res.push(series);
                }
                else if (this.questionnaire[index].type === "sort") {
                    let series = this.getSortSeries(index);
                    res.push(series);
                }

                return res;
            },
            getHeatXAxis(index) {
                let axis = {
                    type: "category",
                    data: [],
                    splitArea: {
                        show: true
                    }
                };

                for (let i = 1; i <= this.questionnaire[index].data.content.labels.length; i++) {
                    axis.data.push("第" + i.toString() + "位");
                }

                return axis;
            },
            getHeatYAxis(index) {
                return {
                    type: "category",
                    data: this.questionnaire[index].data.content.labels,
                    splitArea: {
                        show: true
                    }
                };
            }
        }
    }
</script>

<style scoped>

</style>