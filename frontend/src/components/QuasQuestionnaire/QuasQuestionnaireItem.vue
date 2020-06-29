<template>
    <div>
        <quas-markdown-text v-model="information" :editable="editable" :markdown="information"/>
        <div v-if="showMoreSettingsIcon">
            <i class="iconfont icon-icon_shezhi quas-setting-button" @click="showModal" />
        </div>

        <quas-check-box-group v-if="type == 'check'" :editable="editable" v-model="content"/>
        <quas-date-picker v-else-if="type == 'date' && !editable"/>
        <quas-dropdown v-else-if="type == 'drop'" :editable="editable" v-model="content"/>
        <quas-radio-group v-else-if="type == 'radio'" :editable="editable" v-model="content" :name="information"/>
        <quas-rich-text v-else-if="type == 'rich' && !editable"/>
        <quas-sort-list v-else-if="type == 'sort'" :editable="editable" v-model="content"/>
        <quas-text-box v-else-if="type == 'text' && !editable"/>

        <quas-setting-modal ref="modal" title="高级设置">
            <template slot="content">
                <div v-if="type == 'check'">
                    <quas-text-box label="最少需要选择：" type="class" :reg="/^\d+$/" v-model="check_min"/>
                    <br/>
                    <quas-text-box label="最多可以选择：" type="class" :reg="/^\d+$/" v-model="check_max"/>
                </div>
                <div v-else-if="type == 'date'">
                    <label>日期选择类型：</label>
                    <quas-radio-group name="radio_type" :items="date_type_label_list" v-model="date_type_res"/>
                    <br/>
                    <quas-check-box-group :items="date_range_label_list" v-model="date_range_res"/>
                    <br/>
                    <label>有效日期范围：</label>
                    <quas-date-picker :range="true" :type="date_type" v-model="date_range"/>
                </div>
                <div v-else-if="type == 'drop'">
                    <!--TODO:-->
                </div>
                <div v-else-if="type == 'rich'">
                    <!--TODO:-->
                </div>
                <div v-else-if="type == 'text'">
                    <!--TODO:-->
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
            type: {
                type: String,
                default: "none"
            },
            editable: {
                type: Boolean,
                default: false
            },
            value: {
                type: Object,
                required: true
            }
        },
        data() {
            return {
                content: this.value.content,
                information: this.value.information,
                more_settings_type: ["check", "date", "drop", "rich", "text"],
                check_max: "0",
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
                }
            };
        },
        watch: {
            information(new_value) {
                this.$emit("input", {
                    information: new_value,
                    content: this.content
                });
            },
            content: {
                handler(new_value) {
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
                    this.date_type = this.date_type_list[this.date_type_label_list.indexOf(new_value.result)];
                },
                deep: true
            },
            date_range: {
                handler(new_value) {
                    this.content.min = new_value.result.begin;
                    this.content.max = new_value.result.end;
                },
                deep: true
            }
        },
        computed: {
            showMoreSettingsIcon() {
                return this.more_settings_type.indexOf(this.type) > -1;
            }
        },
        methods: {
            showModal() {
                this.$refs.modal.show();
            }
        },
        beforeMount() {
            if (this.type === "check" && this.editable) {
                this.check_max = this.content.max.toString();
            }
        }
    }
</script>

<style scoped>

</style>