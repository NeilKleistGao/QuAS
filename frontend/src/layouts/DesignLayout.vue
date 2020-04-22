<template>
    <div>
        <h1>设计模式测试</h1>
        <hr>
        <quas-radio-group :editable="true" v-model="radio_items"/>
    </div>
</template>

<script>
    import QuasRadioGroup from "@/components/QuasRadioGroup";

    export default {
        name: "DesignLayout",
        components: {QuasRadioGroup},
        data() {
            return {
                radio_items: {
                    labels: ["选项1", "选项2", "选项3"]
                }
            };
        },
        watch: {
            "radio_items.labels"(new_value){
                console.log(new_value);
            }
        }
    }
</script>

<style scoped>

</style>