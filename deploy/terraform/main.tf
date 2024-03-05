resource "aws_vpc" "jwt_provider_vpc_1" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true


  tags = {
    "Name" = "jwt_provider_vpc_1"
  }
}

resource "aws_subnet" "jwt_provider_subnet" {
  vpc_id                  = aws_vpc.jwt_provider_vpc_1.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "jwt_provider_subnet"
  }
}

resource "aws_internet_gateway" "jwt_provider_igw_1a" {
  vpc_id = aws_vpc.jwt_provider_vpc_1.id

  tags = {
    "Name" = "jwt_provider_igw_1a"
  }
}

resource "aws_route_table" "jwt_provider_rtb_pub" {
  vpc_id = aws_vpc.jwt_provider_vpc_1.id

  tags = {
    "Name" = "jwt_provider_rtb_pub"
  }
}

resource "aws_route" "jwt_provider_default_rtb" {
  route_table_id         = aws_route_table.jwt_provider_rtb_pub.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.jwt_provider_igw_1a.id
}

resource "aws_route_table_association" "jwt_provider_rtba_pub_1a" {
  route_table_id = aws_route_table.jwt_provider_rtb_pub.id
  subnet_id      = aws_subnet.jwt_provider_subnet.id
}


resource "aws_instance" "jwt_provider_ec2_inst" {
  instance_type          = "t2.micro"
  key_name               = aws_key_pair.jwt_provider_key.id
  vpc_security_group_ids = [aws_security_group.jwtprovider_sg.id]
  subnet_id              = aws_subnet.jwt_provider_subnet.id
  ami                    = data.aws_ami.jwt_provider_ami.id

  user_data = file("userdata.tpl")

  root_block_device {
    volume_size = 8
  }

  tags = {
    "Name" = "jwt_provider_ec2_inst"
  }
}