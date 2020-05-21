<template>
    <div>
        <div v-if="!editable">
            <article v-html="compileMarkdown"></article>
        </div>
        <div v-else>
            <i class="iconfont icon-charutupian"/>
            <i class="iconfont icon-shengyinkai"/>
            <i class="iconfont icon-shexiangji"/>

            <div class="quas-rich-text" contenteditable="true" @input="updateText" ref="box"/>
        </div>
    </div>
</template>

<script>
    import marked from "marked";

    export default {
        name: "QuasMarkdownText",
        props: {
            editable: {
                type: Boolean,
                default: false
            },
            markdown: {
                type: String
            },
            value: {
                type: String
            }
        },
        computed: {
            compileMarkdown() {
                return marked(this.markdown, { sanitize: true });
            }
        },
        methods: {
            updateText() {
                let text = this.$refs.box.innerHTML;
                this.$emit("input", text);
            }
        },
        beforeMount() {
            if (this.editable) {
                this.$refs.box.innerHTML = this.value;
            }
        }
    }
</script>

<style scoped>
    i {
        color: gray;
        opacity: 50%;
        width: 64px;
        height: 64px;
        margin-left: 0.3em;
        margin-right: 0.3rem;
        transition: all 0.3s;
    }

    i:hover {
        color: var(--primary);
        opacity: 100%;
    }

    div .quas-rich-text {
        height: 80px;
    }
</style>