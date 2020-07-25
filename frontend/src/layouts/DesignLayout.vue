<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links" :active_index="0"/>
        <quas-side-tools @callback="insert"/>

        <div class="quas-design-container">
            <div class="quas-design-header">
                <h2 style="display: inline-block">问卷标题：</h2>
                <quas-text-box style="display: inline-block"/>
                <br/>
            </div>

            <div v-for="(item, index) in questionnaire" :key="index">
                <div style="margin-left: 20px">
                    <h4 style="margin-left: 40px">第{{index + 1}}题</h4>
                    <quas-questionnaire-item style="margin-left: 80px"
                                             class="quas-ques-item"
                                             :type="item.type"
                                             v-model="item.data"
                                             :editable="true"
                                            :problems_list="getProblemsList(index + 1)"/>
                </div>
                <br/>
            </div>

            <div style="text-align: center">
                <button class="quas-submit-button">保存修改</button>
            </div>

        </div>
    </div>
</template>

<script>

    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasSideTools from "@/components/QuasWebUI/QuasSideTools";
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    import QuasQuestionnaireItem from "@/components/QuasQuestionnaire/QuasQuestionnaireItem";
    export default {
        name: "DesignLayout",
        components: {QuasQuestionnaireItem, QuasTextBox, QuasSideTools, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154"],
                questionnaire: [
                    {
                        type: "none",
                        data: {
                            information: "开始设计您的问卷",
                            content: null,
                            next: 0
                        }
                    }
                ]
            };
        },
        methods: {
            insert(label) {
                let new_item = {
                    type: label
                };
                if (label === "radio") {
                    new_item["data"] = {
                        information: "单选题",
                        content: {
                            labels: ["选项"],
                            nullable: false,
                            next: [0]
                        },
                        next: 0
                    };
                }
                else if (label === "check") {
                    new_item["data"] = {
                        information: "多选题",
                        content: {
                            labels: ["选项"],
                            min: 0,
                            max: 1,
                            nullable: false,
                        },
                        next: 0
                    };
                }
                else if (label === "text") {
                    new_item["data"] = {
                        information: "填空题",
                        content: {
                            type: "text",
                            reg: null,
                            nullable: false
                        },
                        next: 0
                    };
                }
                else if (label === "rich") {
                    new_item["data"] = {
                        information: "多行文本",
                        content: {
                            min: 0,
                            max: 999,
                            nullable: false
                        },
                        next: 0
                    };
                }
                else if (label === "date") {
                    new_item["data"] = {
                        information: "日期",
                        content: {
                            type: "date",
                            range: false,
                            limit: false,
                            min: "2020-01-01",
                            max: "2020-01-01",
                            nullable: false
                        },
                        next: 0
                    };
                }
                else if (label === "drop") {
                    new_item["data"] = {
                        information: "下拉菜单",
                        content: {
                            labels: ["标题", "标题", "标题", "标题", "标题", "标题"],
                            items: [{
                                label: "选项",
                                level: 0,
                            }],
                            nullable: false
                        },
                        next: 0
                    };
                }
                else if (label === "sort") {
                    new_item["data"] = {
                        information: "排序题",
                        content: {
                            labels: ["选项"],
                            nullable: false
                        },
                        next: 0
                    };
                }
                else if (label === "none") {
                    new_item["data"] = {
                        information: "文本说明",
                        content: null,
                        next: 0
                    };
                }
                else {
                    return;
                }

                this.questionnaire.push(new_item);
            },
            getProblemsList(index) {
                let res = [];

                res.push("下一题");
                for (let i = index + 1; i <= this.questionnaire.length; i++) {
                    res.push("第" + i.toString() + "题");
                }
                res.push("结束");

                console.log(index.toString() + ":" + res);

                return res;
            }
        },
        beforeMount() {
            let platform = require("../utils/platform");
            if (!platform.isPC()) {
                alert("请在电脑端完成编辑");
            }
        }
    }
</script>

<style scoped>

</style>