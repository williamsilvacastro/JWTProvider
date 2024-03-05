resource "aws_security_group" "jwtprovider_sg" {
  name        = "jwtprovider_name_sg"
  description = "jwtprovider security group"
  vpc_id      = aws_vpc.jwt_provider_vpc_1.id

}

resource "aws_security_group_rule" "sgr_out_pub" {
  from_port         = 0
  protocol          = "-1"
  security_group_id = aws_security_group.jwtprovider_sg.id
  to_port           = 0
  type              = "egress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "sgr_ssh_in" {
  from_port         = 22
  protocol          = "tcp"
  security_group_id = aws_security_group.jwtprovider_sg.id
  to_port           = 22
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "sgr_http_in" {
  from_port         = 80
  protocol          = "tcp"
  security_group_id = aws_security_group.jwtprovider_sg.id
  to_port           = 80
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}


resource "aws_key_pair" "jwt_provider_key" {
  key_name   = "jwt_provider_key"
  public_key = file("~/.ssh/jwtprovider_key.pub")
}
