<template>
    <div>
        <h1>填写模式测试</h1>
        <hr>
        <quas-radio-group :items="radio_items" v-model="radio_result" name="test"/>
        <br>
        <quas-check-box-group :items="check_items" v-model="check_result"/>
        <br>
        <quas-text-box label="文本框："/>
        <quas-text-box label="输入数字：" type="number"/>
        <quas-text-box label="带正则校验的文本框：" type="class" :reg="reg"/>
        <br>
        <quas-rich-text label="富文本框" :min="10"/>
        <br>
        <quas-sort-list v-model="sort_result"/>
        <br/>
        <quas-dropdown placeholder="请选择" :contents="dropdown_contents" v-model="dropdown_result"/>
        <br/>
        <quas-date-picker type="date" v-model="date_result"/>
        <quas-date-picker type="month" :range="true" v-model="month_result"/>
        <br/>
        <quas-markdown-text :markdown="markdown"/>
    </div>
</template>

<script>
    import QuasRadioGroup from "@/components/QuasRadioGroup";
    import QuasCheckBoxGroup from "@/components/QuasCheckBoxGroup";
    import QuasTextBox from "@/components/QuasTextBox";
    import QuasRichText from "@/components/QuasRichText";
    import QuasSortList from "@/components/QuasSortList";
    import QuasDropdown from "@/components/QuasDropdown";
    import QuasDatePicker from "@/components/QuasDatePicker";
    import QuasMarkdownText from "@/components/QuasMarkdownText";

    export default {
        name: "FillLayout",
        components: {
            QuasMarkdownText,
            QuasDatePicker,
            QuasDropdown, QuasSortList, QuasRichText, QuasTextBox, QuasRadioGroup, QuasCheckBoxGroup},
        data() {
            return {
                radio_items: {
                    labels: ["选项1", "选项2", "选项3"]
                },
                radio_result: {
                    result: ""
                },
                check_items: {
                    labels: ["选项4", "选项5", "选项6"],
                    min: -1,
                    max: 2
                },
                check_result: {
                    result: []
                },
                reg: /test/i,
                sort_result: {
                    result: ["选项7", "选项8", "选项9"]
                },
                dropdown_contents: {
                    labels: ["题目1", "题目2", "题目3"],
                    items: [
                        {
                            label: "选项10",
                            level: 0
                        }, {
                            label: "选项11",
                            level: 1
                        },{
                            label: "选项12",
                            level: 2
                        },{
                            label: "选项13",
                            level: 0
                        },{
                            label: "选项14",
                            level: 1
                        },{
                            label: "选项15",
                            level: 1
                        }
                    ]
                },
                dropdown_result: {
                    result: ["", "", "", "", "", ""]
                },
                date_result: {
                    result: "2001-08-28"
                },
                month_result: {
                    result: {
                        begin: "2020-05",
                        end: "2020-09"
                    }
                },
                markdown: "# hello"
            };
        },
        watch: {
            radio_result(new_value) {
                console.log(new_value.result);
            },
            check_result(new_value) {
                console.log(new_value.result);
            }
        }
    }
</script>

<style scoped>

</style>