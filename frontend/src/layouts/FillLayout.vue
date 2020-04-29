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
    </div>
</template>

<script>
    import QuasRadioGroup from "@/components/QuasRadioGroup";
    import QuasCheckBoxGroup from "@/components/QuasCheckBoxGroup";
    import QuasTextBox from "@/components/QuasTextBox";

    export default {
        name: "FillLayout",
        components: {QuasTextBox, QuasRadioGroup, QuasCheckBoxGroup},
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
                reg: /test/i
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