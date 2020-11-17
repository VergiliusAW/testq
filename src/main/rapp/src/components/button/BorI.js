import React from "react";
import LoginButton from "./LoginButton";

export default class BorI extends React.Component {
    state = {
        session: false,
        href: "",
    };

    componentDidMount() {
        const url =
            window.location.protocol +
            "//" +
            window.location.hostname +
            (window.location.port ? ":" + window.location.port : "");
        fetch(url + "/dev/img")
            .then((res) => res.json())
            .then(
                (result) => {
                    this.setState({ session: result.session });
                    if (this.state.session === true) {
                        this.setState({ href: result.href });
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
    }

    render() {
        return (
            <>
                {this.state.session && (
                    <div className={"profile-pic"}>
                        <img src={this.state.href}></img>
                    </div>
                )}
                {!this.state.session && <LoginButton />}
            </>
        );
    }
}
