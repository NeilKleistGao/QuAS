<template>
    <div>
        <div v-if="!editable">

        </div>
        <div v-else>

        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasDropdown",
        props: {
            editable: {
                type: Boolean,
                default: false
            }
        }
    }
</script>

<style scoped>

</style>