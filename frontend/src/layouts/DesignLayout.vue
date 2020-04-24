<template>
    <div>
        <h1>设计模式测试</h1>
        <hr>
        <quas-radio-group :editable="true" v-model="radio_items"/>
        <br>
        <quas-check-box-group :editable="true" v-model="check_items"/>
    </div>
</template>

<script>
    import QuasRadioGroup from "@/components/QuasRadioGroup";
    import QuasCheckBoxGroup from "@/components/QuasCheckBoxGroup";

    export default {
        name: "DesignLayout",
        components: {QuasRadioGroup, QuasCheckBoxGroup},
        data() {
            return {
                radio_items: {
                    labels: ["选项1", "选项2", "选项3"]
                },
                check_items: {
                    labels: ["选项4", "选项5", "选项6"],
                    min: -1,
                    max: 2
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