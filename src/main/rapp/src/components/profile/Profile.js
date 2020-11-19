import React from "react";
import Button from "../button/Button";
import "./Profile.css";

export default class Profile extends React.Component {
    state = {
        isLoaded: false,
        body: "",
        data: {
            action: () => {
                this.submit();
            },
        },
    };

    componentDidMount() {
        this.getData();
    }

    getData() {
        fetch("/api" + window.location.pathname)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log(responseJson);
                this.setState({ body: responseJson.body });
                this.setState({ isLoaded: true });
            })
            .catch((error) => {
                this.setState({ isLoaded: false });
                console.error(error);
            });
    }

    submit() {
        const data = {
            title: document.getElementById("input-title").value,
            body: document.getElementById("text").value,
        };
        fetch("/api/post" + window.location.pathname, {
            method: "post",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        })
            .then((response) => response.json())
            .then((res) => {
                if (res.res === "success") {
                    document.getElementById("input-title").value = "";
                    document.getElementById("text").value = "";
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }

    render() {
        return (
            <>
                {this.state.isLoaded && (
                    <div className={"profile"}>
                        <div className={"profile-body"}>{this.state.body}</div>
                        {!(this.state.body === "403 ОТКАЗАНО В ДОСТУПЕ") && (
                            <>
                                <div className={"form"}>
                                    <span>Title</span>
                                    <input
                                        id={"input-title"}
                                        type={"text"}
                                    ></input>
                                    <span>Body</span>
                                    <textarea
                                        id={"text"}
                                        rows={"4"}
                                        cols={"50"}
                                        name={"comment"}
                                    ></textarea>
                                </div>
                                <Button data={this.state.data}>Submit</Button>
                            </>
                        )}
                    </div>
                )}
            </>
        );
    }
}
