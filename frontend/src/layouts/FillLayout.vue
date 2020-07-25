<template>
    <div>
        <quas-nav :labels="nav_labels" :links="nav_links"/>

        <div class="quas-fill-container">
            <h1 class="quas-fill-header">{{title}}</h1>
            <br/>

            <div v-for="(link, index) in showing" :key="update_key.toString() + '-' + index.toString()">
                <div style="margin-left: 20px; width: 60%">
                    <h4 style="margin-left: 40px">第{{index + 1}}题</h4>
                    <quas-questionnaire-item style="margin-left: 80px"
                                             class="quas-ques-item"
                                             :type="questionnaire[link].type"
                                             :questionnaire="questionnaire[link].data"
                                             :editable="false"
                                             v-model="results[link]"/>
                    <br/>
                </div>
            </div>

            <br/>
            <div style="text-align: center">
                <button class="quas-submit-button">提交问卷</button>
            </div>
        </div>
        <br/>
    </div>
</template>

<script>
    import QuasNav from "@/components/QuasWebUI/QuasNav";
    import QuasQuestionnaireItem from "@/components/QuasQuestionnaire/QuasQuestionnaireItem";

    export default {
        name: "FillLayout",
        components: {QuasQuestionnaireItem, QuasNav},
        data() {
            return {
                nav_labels: ["问卷设计测试", "问卷填写测试", "问卷结果测试"],
                nav_links: ["#/questionnaire/design/3154", "#/questionnaire/fill/3154", "#/questionnaire/result/3154"],
                title: "关于问卷测试的问卷调查",
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
                results: [
                    {
                    },
                    {
                        result: ""
                    },
                    {
                        result: []
                    },
                    {
                        result: "2020-09-23"
                    },
                    {
                        result: ""
                    },
                    {
                        result: ""
                    },
                    {
                        result: ["", "", "", "", "", ""]
                    },
                    {
                        result: ["选项1", "选项2", "选项3"]
                    }],
                showing: [

                ],
                backup: [],
                update_key: 0
            };
        },
        watch: {
            results: {
                handler(new_value) {
                    for (let i = 0; i < this.results.length; i++) {
                        if (this.questionnaire[i].type === "radio") {
                            if (new_value[i].result.result !== this.backup[i].result) {
                                let selected = -1;
                                for (let j = 0; j < this.questionnaire[i].data.content.labels.length; j++) {
                                    if (new_value[i].result.result === this.questionnaire[i].data.content.labels[j]) {
                                        selected = this.questionnaire[i].data.content.next[j];
                                        break;
                                    }
                                }

                                if (selected === 0) {
                                    selected = i + 1;
                                    this.unfold(selected, i);
                                }
                                else if (selected !== -1) {
                                    this.unfold(selected - 1, i);
                                }

                                this.copyBackup();
                                break;
                            }
                        }
                    }
                },
                deep: true
            }
        },
        beforeMount() {
            let platform = require("../utils/platform");
            if (!platform.isPC()) {
                window.location.href = "#/questionnaire/mobile_fill/3154";
            }

            this.unfold(0, -1);
            this.copyBackup();
        },
        methods: {
            unfold(current, previous) {
                let pos = this.showing.indexOf(previous);
                if (pos > -1) {
                    this.showing.splice(pos + 1, this.showing.length - pos - 1);
                }

                while (current < this.questionnaire.length) {
                    this.showing.push(current);

                    if (this.questionnaire[current].type === "radio") {
                        break;
                    }

                    if (this.questionnaire[current].data.next === 0) {
                        current++;
                    }
                    else if (this.questionnaire[current].data.next === -1) {
                        break;
                    }
                    else {
                        current = this.questionnaire[current].data.next - 1;
                    }
                }
            },
            copyBackup() {
                this.backup.length = 0;
                for (let r of this.results) {
                    this.backup.push(r);
                }
            }
        }
    }
</script>

<style scoped>

</style>