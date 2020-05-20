<template>
    <div>
        <h1>设计模式测试</h1>
        <hr>
        <quas-radio-group :editable="true" v-model="radio_items"/>
        <br>
        <quas-check-box-group :editable="true" v-model="check_items"/>
        <br/>
        <quas-sort-list :editable="true" v-model="sort_items"/>
        <br/>
        <quas-dropdown :editable="true" v-model="dropdown_items"/>
        <br/>
        <p @click="showModal">点我展示弹窗</p>
        <quas-setting-modal ref="modal" title="modal">
            <div slot="content">
                rua
            </div>
        </quas-setting-modal>
    </div>
</template>

<script>
    import QuasRadioGroup from "@/components/QuasRadioGroup";
    import QuasCheckBoxGroup from "@/components/QuasCheckBoxGroup";
    import QuasSortList from "@/components/QuasSortList";
    import QuasDropdown from "@/components/QuasDropdown";
    import QuasSettingModal from "@/components/QuasSettingModal";

    export default {
        name: "DesignLayout",
        components: {QuasSettingModal, QuasDropdown, QuasSortList, QuasRadioGroup, QuasCheckBoxGroup},
        data() {
            return {
                radio_items: {
                    labels: ["选项1", "选项2", "选项3"]
                },
                check_items: {
                    labels: ["选项4", "选项5", "选项6"],
                    min: -1,
                    max: 2
                },
                sort_items: {
                    labels: ["选项7", "选项8", "选项9"]
                },
                dropdown_items: {
                    labels: ["1", "2", "3", "4", "5", "6"],
                    items: [{
                        label: "选项10",
                        level: 0
                    }, {
                        label: "选项11",
                        level: 1
                    },{
                        label: "选项12",
                        level: 2
                    },{
                        label: "选项13",
                        level: 3
                    },{
                        label: "选项14",
                        level: 4
                    },{
                        label: "选项15",
                        level: 5
                    }]
                },
                show: false
            };
        },
        watch: {
            "radio_items.labels"(new_value){
                console.log(new_value);
            }
        },
        methods: {
            showModal() {
                this.$refs.modal.show();
            }
        }
    }
</script>

<style scoped>

</style>