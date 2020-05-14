<template>
    <div>
        <div v-if="!editable">

        </div>
        <div v-else>
            <quas-dropdown-tree v-model="items"/>
        </div>
    </div>
</template>

<script>
    import QuasDropdownTree from "@/components/QuasDropdownTree";
    export default {
        name: "QuasDropdown",
        components: {QuasDropdownTree},
        props: {
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
                result: this.value.result,
                labels: this.value.labels,
                items: this.value.items
            };
        },
        methods: {
            getTitle(index) {
                return "标题" + index.toString();
            }
        }
    }
</script>

<style scoped>
    label, input[type="text"] {
        font-size: 16px;
    }
    label {
        margin-left: 0.3rem;
        margin-right: 0.5rem;
    }
</style>