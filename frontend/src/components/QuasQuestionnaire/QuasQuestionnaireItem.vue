<template>
    <div>
        <quas-markdown-text v-if="editable" v-model="information" :editable="editable" :markdown="information"/>
        <quas-markdown-text v-else :editable="editable" :markdown="questionnaire.information"/>
        <div v-if="showMoreSettingsIcon && editable">
            <i class="iconfont icon-icon_shezhi quas-setting-button" @click="showModal" />
        </div>

        <div v-if="editable">
            <quas-check-box-group v-if="type === 'check'" :editable="editable" v-model="content"/>
            <quas-dropdown v-else-if="type === 'drop'" :editable="editable" v-model="content"/>
            <quas-radio-group v-else-if="type === 'radio'" :editable="editable" v-model="content" :name="information"/>
            <quas-sort-list v-else-if="type === 'sort'" :editable="editable" v-model="content"/>
        </div>
        <div v-else>
            <quas-check-box-group v-if="type === 'check'" :editable="editable" :items="questionnaire.content" v-model="result"/>
            <quas-date-picker v-else-if="type === 'date'" :type="questionnaire.content.type" v-model="result"/>
            <quas-dropdown v-else-if="type === 'drop'" :editable="editable" v-model="result" :contents="questionnaire.content"/>
            <quas-radio-group v-else-if="type === 'radio'" :editable="editable" :items="questionnaire.content" :name="questionnaire.information" v-model="result"/>
            <quas-rich-text v-else-if="type === 'rich'" v-model="result.result"/>
            <quas-sort-list v-else-if="type === 'sort'" :editable="editable" v-model="result" />
            <quas-text-box v-else-if="type === 'text'" v-model="result.result"/>
        </div>

        <quas-setting-modal ref="modal" title="高级设置" :key="update_key" v-if="editable">
            <template slot="content">
                <div v-if="type === 'check'">
                    <quas-text-box label="最少需要选择：" :reg="/^\d+$/" v-model="check_min"/>
                    <br/>
                    <quas-text-box label="最多可以选择：" :reg="/^\d+$/" v-model="check_max"/>
                </div>
                <div v-else-if="type === 'date'">
                    <label>日期选择类型：</label>
                    <quas-radio-group name="radio_type" :items="date_type_label_list" v-model="date_type_res"/>
                    <br/>
                    <quas-check-box-group :items="date_range_label_list" v-model="date_range_res"/>
                    <br/>
                    <label>有效日期范围：</label>
                    <quas-date-picker :range="true" :type="date_type" v-model="date_range"/>
                </div>
                <div v-else-if="type === 'drop'">
                    <div v-for="(label, index) in content.labels" :key="index">
                        <quas-text-box v-if="index <= drop_max" :label="'标题' + (index + 1).toString()" type="text" v-model="content.labels[index]"/>
                    </div>
                </div>
                <div v-else-if="type === 'rich'">
                    <quas-text-box label="最少字数：" :reg="/^\d+$/" v-model="rich_min"/>
                    <br/>
                    <quas-text-box label="最多字数：" :reg="/^\d+$/" v-model="rich_max"/>
                </div>
                <div v-else-if="type === 'text'">
                    <label>文本类型：</label>
                    <quas-radio-group name="text_type" :items="text_type_label_list" v-model="text_type_res"/>
                    <quas-text-box label="正则表达式：" v-model="text_reg"/>
                </div>
                <div v-else>
                    <p>当前的题目不支持更多的设置</p>
                </div>
            </template>
        </quas-setting-modal>
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
    import QuasSettingModal from "@/components/QuasQuestionnaire/QuasSettingModal";
    export default {
        name: "QuasQuestionnaireItem",
        components: {
            QuasSettingModal,
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
            }
        },
        data() {
            return {
                content: this.value.content,
                information: this.value.information,
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
                        begin: "",
                        end: ""
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
                text_reg: ""
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
                    content: this.content
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
                        content: new_value
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
                            min: num_min
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
                            min: num_min
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
            }
        },
        computed: {
            showMoreSettingsIcon() {
                return this.more_settings_type.indexOf(this.type) > -1;
            }
        },
        methods: {
            /**
             * 显示更多设置的modal
             * @private
             */
            showModal() {
                this.$refs.modal.show();
            }
        }
    }
</script>

<style scoped>

</style>