<template>
    <div>
        <div v-if="!range">
            <input :type="type" v-model="result" :min="min" :max="max" class="quas-date-picker">
        </div>
        <div v-else>
            <input :type="type" v-model="range_result.begin" :min="min" :max="first_max" class="quas-date-picker"/>
            <label>-</label>
            <input :type="type" v-model="range_result.end" :min="last_min" :max="max" class="quas-date-picker"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasDatePicker",
        props: {
            /**
             * 日期选择类型，可选值包括date, week, month, time
             */
            type: {
                type: String,
                required: true
            },
            /**
             * 日期选择结果，范围选择包括两个值begin和end
             */
            value: {
                type: Object,
                required: true
            },
            /**
             * 最早可以选择的日期，如果为空则表示不设下限
             */
            min: {
                type: String,
                default: ""
            },
            /**
             * 最晚可以选择的日期，如果为空则表示不设上限
             */
            max: {
                type: String,
                default: ""
            },
            /**
             * 是否为范围选择，默认为否
             */
            range: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                result: this.value.result,
                range_result: {
                    begin: this.value.result.begin,
                    end: this.value.result.end
                },
                first_max: this.value.result.end,
                last_min: this.value.result.begin
            }
        },
        watch: {
            result(new_value) {
                /**
                 * 更新所选日期
                 * @event{oninput}
                 */
                this.$emit("input", {
                    result: new_value
                });
            },
            range_result: {
                handler(new_value) {
                    /**
                     * 更新所选范围
                     * @event{oninput}
                     */
                    this.$emit("input", {
                        result: new_value
                    });

                    this.first_max = this.range_result.end;
                    this.last_min = this.range_result.begin;
                },
                deep: true
            }
        }
    }
</script>

<style scoped>

</style>