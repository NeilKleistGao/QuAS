<template>
    <div>
        <h1>设计模式测试</h1>
        <hr>
        <quas-questionnaire-item :editable="true" v-model="none_item"/>
        <quas-questionnaire-item type="check" :editable="true" v-model="check_item"/>
        <quas-questionnaire-item type="date" :editable="true" v-model="date_item"/>
        <quas-questionnaire-item type="drop" :editable="true" v-model="drop_item"/>
        <quas-questionnaire-item type="radio" :editable="true" v-model="radio_item"/>
        <quas-questionnaire-item type="rich" :editable="true" v-model="rich_item"/>
        <quas-questionnaire-item type="sort" :editable="true" v-model="sort_item"/>
        <quas-questionnaire-item type="text" :editable="true" v-model="text_item"/>
    </div>
</template>

<script>

    import QuasQuestionnaireItem from "@/components/QuasQuestionnaire/QuasQuestionnaireItem";
    export default {
        name: "DesignLayout",
        components: {QuasQuestionnaireItem},
        data() {
            return {
                none_item: {
                    information: "这是一个纯文本测试"
                },
                check_item: {
                    information: "这是一个多选题的测试",
                    content: {
                        labels: ["选项1", "选项2", "选项3"],
                        min: 0,
                        max: 3
                    }
                },
                date_item: {
                    information: "这是一个日期的测试",
                    content: {
                        min: "",
                        max: "",
                        range: false
                    }
                },
                drop_item: {
                    information: "这是一个下拉选项的测试",
                    content: {
                        labels: ["标题1", "标题2", "标题3", "标题4", "标题5", "标题6"],
                        items: [{
                            label: "选项1",
                            level: 0
                        },{
                            label: "选项2",
                            level: 1
                        },{
                            label: "选项3",
                            level: 2
                        },{
                            label: "选项4",
                            level: 3
                        },{
                            label: "选项5",
                            level: 4
                        },{
                            label: "选项6",
                            level: 5
                        }]
                    }
                },
                radio_item: {
                    information: "这是一个单选题测试",
                    content: {
                        labels: ["选项1", "选项2", "选项3"]
                    }
                },
                rich_item: {
                    information: "这是一个富文本框测试",
                    content: {
                        min: -1,
                        max: -1
                    }
                },
                sort_item: {
                    information: "这是一个排序题测试",
                    content: {
                        labels: ["选项1", "选项2", "选项3"]
                    }
                },
                text_item: {
                    information: "这是一个文本框测试",
                    content: {
                        type: "text",
                        reg: null
                    }
                }
            };
        },
        methods: {
        },
        watch: {
            check_item: {
                handler(new_value) {
                    console.log(new_value);
                },
                deep: true
            },
            date_item: {
                handler(new_value) {
                    console.log(new_value);
                },
                deep: true
            },
            drop_item: {
                handler(new_value) {
                    console.log(new_value);
                },
                deep: true
            }
        }
    }
</script>

<style scoped>

</style>