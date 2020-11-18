import React from "react";
import LoginButton from "./LoginButton";
import ProfileIco from "./ProfileIco";

export default class BorI extends React.Component {
    state = {
        session: false,
        img: "",
        href: "",
    };

    componentWillMount() {
        this.getData();
    }

    getData() {
        fetch("/dev/img")
            .then((res) => res.json())
            .then(
                (result) => {
                    console.log(result);
                    this.setState({ session: result.session });
                    if (this.state.session === true) {
                        this.setState({ img: result.img });
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
                    <ProfileIco img={this.state.img} href={this.state.href} />
                )}
                {!this.state.session && (
                    <LoginButton
                        action={() => {
                            this.getData();
                            this.setState({ session: true });
                        }}
                    />
                )}
            </>
        );
    }
}
