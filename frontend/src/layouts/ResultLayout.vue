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

                        <div v-if="item.type === 'radio'">
                            <quas-loading-spin label="加载中" :width="48" :height="48" :typing="true" style="text-align: center"/>
                        </div>
                        <div v-else-if="item.type === 'check'">
                            <quas-loading-spin label="加载中" :width="48" :height="48" :typing="true" style="text-align: center"/>
                        </div>
                        <div v-else-if="item.type === 'drop'">
                            <quas-loading-spin label="加载中" :width="48" :height="48" :typing="true" style="text-align: center"/>
                        </div>
                        <span v-else>该类题目暂时不支持绘制统计图表</span>
                        <hr/>
                    </div>

                </div>
            </template>
        </quas-tab>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasTab from "@/components/QuasWebUI/QuasTab";
    import QuasLoadingSpin from "@/components/QuasWebUI/QuasLoadingSpin";
    export default {
        name: "ResultLayout",
        components: {QuasLoadingSpin, QuasTab, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154"],
                title: "关于问卷测试的问卷调查",
                tab_labels: ["填写数据", "统计图表"],
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
                                labels: ["选项１", "选项2", "选项3"],
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
                                labels: ["选项１", "选项2", "选项3"]
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
                    [{},{result: "选项1"},{result: ["选项1"]},{result: "2020-09-23"},{result: "foo"},{result: "bar"},{result: ["选项1"]},{result: ["选项1", "选项2", "选项3"]}],
                    [{},{result: "选项2"},{result: ["选项1", "选项2"]},{result: "2020-09-23"},{result: "foo"},{result: "bar"},{result: ["选项1"]},{result: ["选项1", "选项2", "选项3"]}],
                    [{},{result: "选项2"},{result: ["选项1", "选项2", "选项3"]},{result: "2020-09-23"},{result: "foo"},{result: "bar"},{result: ["选项1"]},{result: ["选项1", "选项2", "选项3"]}]
                ]
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
            }
        }
    }
</script>

<style scoped>

</style>