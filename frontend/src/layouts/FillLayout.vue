<template>
    <div>
        <h1>填写模式测试</h1>
        <hr>
        <quas-radio-group :items="radio_items" v-model="radio_result" name="test"/>
    </div>
</template>

<script>
    import QuasRadioGroup from "@/components/QuasRadioGroup";

    export default {
        name: "FillLayout",
        components: {QuasRadioGroup},
        data() {
            return {
                radio_items: {
                    labels: ["选项1", "选项2", "选项3"]
                },
                radio_result: {
                    result: ""
                }
            };
        },
        watch: {
            "radio_result"(new_value) {
                console.log(new_value.result);
            }
        }
    }
</script>

<style scoped>

</style>