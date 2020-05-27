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
            type: {
                type: String,
                required: true
            },
            value: {
                type: Object,
                required: true
            },
            min: {
                type: String,
                default: ""
            },
            max: {
                type: String,
                default: ""
            },
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
                this.$emit("input", {
                    result: new_value
                });
            },
            range_result: {
                handler(new_value) {
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