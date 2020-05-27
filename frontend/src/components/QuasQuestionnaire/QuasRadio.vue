<template>
    <div>
        <div v-if="!editable">
            <input ref="box" class="quas-radio" type="radio" :id="text" :name="name" @click="updateSelect">
            <label :for="text"> {{text}} </label>
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
                type: String,
                required: true
            },
            name: {
                type: String,
                required: true
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
        },
        beforeMount() {
            if (this.selected_text === this.text) {
                this.$refs.box.checked = true;
            }
        }
    }
</script>

<style scoped>
    @import "../../style/form/questionnaire.css";

    label, input[type="text"] {
        font-size: 16px;
    }
    label {
        margin-left: 0.3rem;
    }
</style>