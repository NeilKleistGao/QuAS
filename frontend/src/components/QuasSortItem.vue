<template>
    <div>
        <div v-if="!editable">
            <input class="quas-sort-item" ref="box" type="checkbox">
            <label :for="text"> {{text}} </label>
        </div>
        <div v-else>
            <input type="checkbox" class="quas-design-sort" disabled>
            <input class="quas-design-text" type="text" v-model="text">
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasSortItem",
        props: {
            editable: {
                type: Boolean,
                default: false
            },
            label: {
                type: String
            },
            value: {
                type: String,
                required: true
            },
            index: {
                type: Number,
                default: -1
            }
        },
        data() {
            return {
                text: this.value
            };
        },
        watch: {
            text(new_value) {
                this.$emit("input", new_value);
            }
        }
    }
</script>

<style scoped>

</style>