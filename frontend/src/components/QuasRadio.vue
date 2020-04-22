<template>
    <div>
        <div v-if="!editable">
            <input ref="radio" class="quas-radio" type="radio" :id="text" :name="name" @click="updateSelect">
            <label v-if="!editable" :for="text"> {{text}} </label>
        </div>
        <div v-else>
            <input class="quas-design-radio" type="radio" disabled>
            <input class="quas-design-text" type="text" v-model="label_text">
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasRadio",
        props: {
            editable: {
                type: Boolean,
                default: false
            },
            text: {
                type: String
            },
            value: {
                type: String
            },
            name: {
                type: String
            }
        },
        data() {
            return {
                label_text: this.value,
                selected_text: this.value
            };
        },
        methods: {
            updateSelect() {
                this.selected_text = this.text;
                this.$emit("input", this.selected_text);
            }
        },
        watch: {
            label_text(new_value) {
                this.$emit("input", new_value);
            }
        }
    }
</script>

<style scoped>
    @import "../style/form/form.css";

    label, input[type="text"] {
        font-size: 16px;
    }
    label {
        margin-left: 0.3rem;
    }
</style>