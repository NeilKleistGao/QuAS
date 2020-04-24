<template>
    <div>
        <h1>填写模式测试</h1>
        <hr>
        <quas-radio-group :items="radio_items" v-model="radio_result" name="test"/>
        <br>
        <quas-check-box-group :items="check_items" v-model="check_result"/>
    </div>
</template>

<script>
    import QuasRadioGroup from "@/components/QuasRadioGroup";
    import QuasCheckBoxGroup from "@/components/QuasCheckBoxGroup";

    export default {
        name: "FillLayout",
        components: {QuasRadioGroup, QuasCheckBoxGroup},
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
                }
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