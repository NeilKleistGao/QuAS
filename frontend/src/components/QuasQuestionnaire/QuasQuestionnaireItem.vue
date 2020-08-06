<template>
    <div>
        <div v-if="editable" class="quas-design-grid">
            <div>
                <quas-markdown-text v-model="information" :editable="editable" :markdown="information"/>
                <br/>
                <quas-check-box v-if="type !== 'none'" :editable="false" label="选做" v-model="nullable_list"/>
                <br/>

                <div v-if="type === 'check'">
                    <label>最多可选：</label>
                    <quas-text-box style="display: inline-block" :reg="/^\d+$/" v-model="check_min"/>
                    <br/>
                    <label>最少需要选：</label>
                    <quas-text-box style="display: inline-block" :reg="/^\d+$/" v-model="check_max"/>
                </div>
                <div v-else-if="type === 'drop'">
                    <div v-for="(label, index) in content.labels" :key="index">
                        <label v-if="index <= drop_max">标题{{(index + 1).toString()}}：</label>
                        <quas-text-box v-if="index <= drop_max" type="text" v-model="content.labels[index]"/>
                        <br v-if="index <= drop_max"/>
                    </div>
                </div>
                <div v-else-if="type === 'date'">
                    <label>日期选择类型：</label>
                    <quas-radio-group name="radio_type" :items="date_type_label_list" v-model="date_type_res"/>
                    <br/>
                    <quas-check-box v-if="type !== 'none'" :editable="false" label="范围选择" v-model="is_range"/>
                    <quas-check-box :editable="false" label="限制有效日期范围" v-model="is_limit"/>
                    <div v-if="content.limit">
                        <br/>
                        <label>有效日期范围：</label>
                        <quas-date-picker :range="true" :type="date_type" v-model="date_range"/>
                    </div>
                </div>
                <div v-else-if="type === 'rich'">
                    <label>最少字数：</label>
                    <quas-text-box style="display: inline-block" :reg="/^\d+$/" v-model="rich_min"/>
                    <br/>
                    <label>最多字数：</label>
                    <quas-text-box style="display: inline-block" :reg="/^\d+$/" v-model="rich_max"/>
                </div>
                <div v-else-if="type === 'text'">
                    <label>文本类型：</label>
                    <quas-radio-group name="text_type" :items="text_type_label_list" v-model="text_type_res"/>
                    <label>正则表达式：</label>
                    <quas-text-box style="display: inline-block" v-model="text_reg"/>
                </div>
            </div>

            <div>
                <br/>
                <quas-check-box-group v-if="type === 'check'" :editable="editable" v-model="content"/>
                <quas-dropdown v-else-if="type === 'drop'" :editable="editable" v-model="content"/>
                <quas-radio-group v-else-if="type === 'radio'" :editable="editable" v-model="content" :name="information"/>
                <quas-sort-list v-else-if="type === 'sort'" :editable="editable" v-model="content"/>
            </div>

            <div style="margin-top: -48px">
                <quas-linker v-if="type !== 'radio'" label="默认跳转：" :items="problems_list" v-model="next"/>
                <div v-if="type === 'radio'" style="margin-top: 28px">
                </div>
                <br/>
                <div v-if="type === 'radio'">
                    <quas-linker v-for="(item, index) in content.labels" :key="index" label="选中跳转：" :items="problems_list" v-model="content.next[index]"/>
                </div>
            </div>

        </div>
        <div v-else>
            <quas-markdown-text :editable="editable" :markdown="questionnaire.information"/>

            <quas-check-box-group v-if="type === 'check'" :editable="editable" :items="questionnaire.content" v-model="result"/>
            <quas-date-picker v-else-if="type === 'date'" :type="questionnaire.content.type" v-model="result"/>
            <quas-dropdown v-else-if="type === 'drop'" :editable="editable" v-model="result" :contents="questionnaire.content"/>
            <quas-radio-group v-else-if="type === 'radio'" :editable="editable" :items="questionnaire.content" :name="questionnaire.information" v-model="result"/>
            <quas-rich-text v-else-if="type === 'rich'" v-model="result.result"/>
            <quas-sort-list v-else-if="type === 'sort'" :editable="editable" v-model="result" />
            <quas-text-box v-else-if="type === 'text'" v-model="result.result"/>
        </div>
    </div>
</template>

<script>
    import QuasMarkdownText from "@/components/QuasQuestionnaire/QuasMarkdownText";
    import QuasCheckBoxGroup from "@/components/QuasQuestionnaire/QuasCheckBoxGroup";
    import QuasDatePicker from "@/components/QuasQuestionnaire/QuasDatePicker";
    import QuasDropdown from "@/components/QuasQuestionnaire/QuasDropdown";
    import QuasRadioGroup from "@/components/QuasQuestionnaire/QuasRadioGroup";
    import QuasRichText from "@/components/QuasQuestionnaire/QuasRichText";
    import QuasSortList from "@/components/QuasQuestionnaire/QuasSortList";
    import QuasTextBox from "@/components/QuasQuestionnaire/QuasTextBox";
    import QuasCheckBox from "@/components/QuasQuestionnaire/QuasCheckBox";
    import QuasLinker from "@/components/QuasQuestionnaire/QuasLinker";
    export default {
        name: "QuasQuestionnaireItem",
        components: {
            QuasLinker,
            QuasCheckBox,
            QuasTextBox,
            QuasSortList,
            QuasRichText, QuasRadioGroup, QuasDropdown, QuasDatePicker, QuasCheckBoxGroup, QuasMarkdownText},
        props: {
            /**
             * 问卷题目的类型
             */
            type: {
                type: String,
                default: "none"
            },
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 非可编辑模式下为问卷填写结果；否则为问卷编辑数据，包含information（提示信息）和content（问卷内容）两个字段
             */
            value: {
                type: Object,
                required: true
            },
            /**
             * 非可编辑模式下问卷数据对象，包含information（提示信息）和content（问卷内容）两个字段
             */
            questionnaire: {
                type: Object
            },
            /**
             * 可跳转页面列表
             */
            problems_list: {
                type: Array
            }
        },
        data() {
            return {
                content: this.value.content,
                information: this.value.information,
                next: this.value.next,
                result: this.value,
                more_settings_type: ["check", "date", "drop", "rich", "text"],
                check_max: "1",
                check_min: "0",
                date_type_label_list: {
                    labels: ["年、月、日", "年、月", "年、周", "小时、分钟"]
                },
                date_type_list: ["date", "month", "week", "time"],
                date_type_res: {
                    result: "年、月、日"
                },
                date_type: "date",
                date_range_label_list: {
                    labels: ["范围选择"],
                    min: 0,
                    max: 1
                },
                date_range_res: {
                    result: []
                },
                date_range: {
                    result: {
                        begin: "2020-01-01",
                        end: "2020-08-06"
                    }
                },
                drop_max: 0,
                update_key: 0,
                rich_min: "0",
                rich_max: "0",
                text_type_label_list: {
                    labels: ["文本", "数字", "链接", "邮箱"]
                },
                text_type_list: ["text", "number", "url", "email"],
                text_type_res: {
                    result: "文本"
                },
                text_reg: "",
                nullable_list: [],
                is_range: [],
                is_limit: []
            };
        },
        watch: {
            information(new_value) {
                /**
                 * 更新问卷提示信息
                 * @event{oninput}
                 */
                this.$emit("input", {
                    information: new_value,
                    content: this.content,
                    next: this.next
                });
            },
            result: {
                handler(new_value) {
                    /**
                     * 更新问卷填写结果
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        result: new_value
                    })
                },
                deep: true
            },
            content: {
                handler(new_value) {
                    if (this.editable) {
                        if (this.type === "check") {
                            this.check_max = this.content.max.toString();
                        }
                        else if (this.type === "drop") {
                            this.drop_max = 0;
                            for (let item of this.content.items) {
                                this.drop_max = (this.drop_max < item.level) ? item.level : this.drop_max;
                            }
                        }

                        this.update_key++;
                    }

                    /**
                     * 更新问卷具体内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        information: this.information,
                        content: new_value,
                        next: this.next
                    });
                },
                deep: true
            },
            check_min(new_value) {
                let num_min = parseInt(new_value);
                let num_max = parseInt(this.check_max);

                if (num_min < num_max) {
                    /**
                     * 更新问卷具体内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        information: this.information,
                        content: {
                            labels: this.content.labels,
                            max: num_max,
                            min: num_min,
                            next: this.content.next
                        }
                    });
                }
            },
            check_max(new_value) {
                let num_min = parseInt(this.check_min);
                let num_max = parseInt(new_value);

                if (num_max > num_min) {
                    num_max = (num_max > this.content.labels.length) ? this.content.labels.length : num_max;
                    /**
                     * 更新问卷具体内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        information: this.information,
                        content: {
                            labels: this.content.labels,
                            max: num_max,
                            min: num_min,
                            next: this.content.next
                        }
                    });
                }
            },
            date_type_res: {
                handler(new_value) {
                    for (let i = 0; i < this.date_type_label_list.length; i++) {
                        if (new_value.result === this.date_type_label_list[i]) {
                            this.date_type = this.date_type_list[i];
                            this.update_key++;
                            break;
                        }
                    }
                },
                deep: true
            },
            date_range: {
                handler(new_value) {
                    this.content.min = new_value.result.begin;
                    this.content.max = new_value.result.end;
                },
                deep: true
            },
            rich_min(new_value) {
                let num_min = parseInt(new_value);
                let num_max = parseInt(this.rich_max);

                if (num_min < num_max) {
                    /**
                     * 更新问卷具体内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        information: this.information,
                        content: {
                            max: num_max,
                            min: num_min
                        }
                    });
                }
            },
            rich_max(new_value) {
                let num_min = parseInt(this.rich_min);
                let num_max = parseInt(new_value);

                if (num_max > num_min) {
                    /**
                     * 更新问卷具体内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        information: this.information,
                        content: {
                            max: num_max,
                            min: num_min
                        }
                    });
                }
            },
            nullable_list: {
                handler(new_value) {
                    let content = this.content;
                    content["nullable"] = (new_value.length === 0);

                    /**
                     * 更新问卷具体内容
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        information: this.information,
                        content: content
                    })
                },
                deep: true
            },
            is_range: {
                handler(new_value) {
                    this.content.range = new_value.length > 0;
                },
                deep: true
            },
            is_limit: {
                handler(new_value) {
                    this.content.limit = new_value.length > 0;
                },
                deep: true
            }
        },
        computed: {
            showMoreSettingsIcon() {
                return this.more_settings_type.indexOf(this.type) > -1;
            }
        },
        beforeMount() {
        }
    }
</script>

<style scoped>
</style>